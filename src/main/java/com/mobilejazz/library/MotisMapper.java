package com.mobilejazz.library;

import java.lang.annotation.Annotation;

import com.mobilejazz.library.annotations.MotisArray;
import com.mobilejazz.library.annotations.MotisClass;
import com.mobilejazz.library.annotations.MotisKey;
import com.mobilejazz.library.annotations.MotisMethod;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MotisMapper {

    private Class clazz;
    private MotisClass motisSetup;

    private HashMap <String, String> mapping;
    private HashMap <String, Class> arrayClassMapping;

    public MotisMapper(Class clazz) {
        super();

        if (clazz.isAnnotationPresent(MotisClass.class)) {

            this.clazz = clazz;

            Annotation annotation = clazz.getAnnotation(MotisClass.class);
            this.motisSetup = (MotisClass)annotation;

            mapping = new HashMap<String, String>();
            arrayClassMapping = new HashMap<String, Class>();

            Field [] fields = clazz.getDeclaredFields();

            for (Field field: fields) {
                String fieldName = field.getName();

                if (field.isAnnotationPresent(MotisKey.class)) {
                    MotisKey motisKey = (MotisKey)field.getAnnotation(MotisKey.class);

                    String jsonName = motisKey.value();
                    mapping.put(jsonName, fieldName);
                } else {
                    if (this.motisSetup.shouldSetUndefinedKeys())
                        mapping.put(fieldName, fieldName);
                }

                if (field.isAnnotationPresent(MotisArray.class)) {
                    MotisArray motisArray = (MotisArray)field.getAnnotation(MotisArray.class);
                    arrayClassMapping.put(fieldName, motisArray.value());
                }
            }
        }
        else
        {
            this.clazz = null;
            this.mapping = null;
            this.motisSetup = null;
        }
    }

    public boolean map(Object object, JSONObject jsonObject) {

        // If motis setup doesn't exist, we cannot proceed.
        if (this.motisSetup == null)
           return false;



        Iterator <String> keys = jsonObject.keys();

        while (keys.hasNext()) {
            String jsonKey = keys.next();

            Object jsonValue = jsonObject.opt(jsonKey);
            // TODO: what about null values?

            this.mapObjectForKey(object, jsonKey, jsonValue);
        }

        return true;
    }

    public void mapObjectForKey(Object object, String jsonKey, Object jsonValue)  {

        Class objectClass = object.getClass();

        String objectKey = this.mapJsonKey(jsonKey);

        if (objectKey == null) {
            // If no object key to map, do nothing.
            return;
        }

        try {
            // Get the field
            Field field = objectClass.getDeclaredField(objectKey);

            Object finalValue = jsonValue;
            boolean validated = true;

            // ----- MANUAL VALIDATION ----- //
            Method validator = this.getMotisMethod("validate", objectKey, MotisValidation.class);

            if (validator != null) {
                // Validate the value if available
                MotisValidation motisValidationObject = new MotisValidation(finalValue);

                boolean accessible = validator.isAccessible();
                validator.setAccessible(true);
                try {
                    validator.invoke(object, motisValidationObject);
                    validated = motisValidationObject.isValid();
                    finalValue = motisValidationObject.getObject();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                validator.setAccessible(accessible);
            }

            // ------ AUTOMATIC VALIDATOIN ------ //
            if (validated && finalValue == jsonValue) {
                // If still valid and the object didn't change, lets do automatic validation
                MotisValidation motisValidationObject = new MotisValidation(finalValue);

                this.automaticValidation(object, objectKey, field, motisValidationObject);

                validated = motisValidationObject.isValid();
                finalValue = motisValidationObject.getObject();
            }

            if (validated) {
                // If validated, set the new value
                boolean accessible = field.isAccessible();

                field.setAccessible(true); // Why setter the accessible to true when is false;


                try {
                    field.set(object, finalValue);
                } catch (IllegalAccessException e) {
                    // TODO: could not set value!
                    System.out.println("IllegalArgumentException: " + e.getMessage());
                }

                field.setAccessible(accessible);
            } else {
                // TODO: value not validated!
                System.out.println("No es valido");
            }
        } catch (NoSuchFieldException e) {
            // TODO : handle exception
            System.out.println("Catch exception: " + e.toString());
        }
    }

    private String mapJsonKey(String jsonKey) {
        String objectKey = mapping.get(jsonKey);

        if (objectKey != null)
            return objectKey;

        if (motisSetup.shouldSetUndefinedKeys())
            return jsonKey;

        return null;
    }

    private void automaticValidation(Object object, String name, Field field, MotisValidation motisValidationObject) {
        Object validationObject = motisValidationObject.getObject();

        Class outClass = field.getType();
        Class inClass = validationObject.getClass();

        if (outClass.equals(inClass)) {
            // Nothing to do, the object is already valid.
            return;
        }

        // If the type in is JSON Object
        if (inClass.equals(JSONObject.class)) {
            this.createInstance(object, "onCreationMotisObject", motisValidationObject, outClass);
        } else if (inClass.equals(JSONArray.class)) {

            JSONArray jsonArray = (JSONArray)validationObject;

            Class arrayItemClass = this.arrayClassMapping.get(name);

            if (arrayItemClass != null) {
                // We know the content type of the array. Lets map the content values.

                if (List.class.isAssignableFrom(outClass)) {
                    try {
                        List list = (List)outClass.newInstance();

                        for (int i=0; i <jsonArray.length(); i++) {
                            JSONObject jsonItem = jsonArray.optJSONObject(i);

                            MotisValidation motisValidation = new MotisValidation(jsonItem);
                            this.createInstance(object, "onCreationMotisArrayObject", motisValidation, arrayItemClass);

                            if (motisValidation.isValid()) {
                                Object newObject = motisValidation.getObject();
                                list.add(newObject);
                            }
                        }

                        // Finally, assign the new list of objects
                        motisValidationObject.setObject(list);

                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                } else {
                    // Motis only supports List-based arrays. Abort.
                    motisValidationObject.setValid(false);
                }

            } else {
                // Inside array objects type is not defined. Abort.
                motisValidationObject.setValid(false);
            }

        } else if (inClass.equals(String.class)) {
            // TODO: Do other cases as Dates, URLS, etc.
            String valueString = (String) motisValidationObject.getObject();
            if (outClass.equals(int.class)) {

                int value = (int) Integer.valueOf(MotisValidationTypes.removedAllSpaces(valueString));
                setMotisValidationObject(motisValidationObject, value, true);

            } else if (outClass.equals(Integer.class)) {

                Integer value = Integer.valueOf(MotisValidationTypes.removedAllSpaces(valueString));
                setMotisValidationObject(motisValidationObject, value, true);

            } else if (outClass.equals(float.class)) {

                float value = (float) Float.valueOf(MotisValidationTypes.removedAllSpaces(valueString));
                setMotisValidationObject(motisValidationObject, value, true);

            } else if (outClass.equals(Float.class)) {

                Float value = Float.valueOf(MotisValidationTypes.removedAllSpaces(valueString));
                setMotisValidationObject(motisValidationObject, value, true);

            } else if (outClass.equals(double.class)) {

                double value = (double) Double.valueOf(MotisValidationTypes.removedAllSpaces(valueString));
                setMotisValidationObject(motisValidationObject, value, true);

            } else if (outClass.equals(Double.class)) {

                Double value = Double.valueOf(MotisValidationTypes.removedAllSpaces(valueString));
                setMotisValidationObject(motisValidationObject, value, true);

            } else if (outClass.equals(long.class)) {

                long value = (long) Long.valueOf(MotisValidationTypes.removedAllSpaces(valueString));
                setMotisValidationObject(motisValidationObject, value, true);

            } else if (outClass.equals(Long.class)) {

                Long value = Long.valueOf(MotisValidationTypes.removedAllSpaces(valueString));
                setMotisValidationObject(motisValidationObject, value, true);

            } else if (outClass.equals(boolean.class)) {

                boolean value = (boolean) Boolean.valueOf(MotisValidationTypes.removedAllSpaces(valueString));
                setMotisValidationObject(motisValidationObject, value, true);

            } else if (outClass.equals(Boolean.class)) {

                Boolean value = Boolean.valueOf(MotisValidationTypes.removedAllSpaces(valueString));
                setMotisValidationObject(motisValidationObject, value, true);

            }

        }

        /**
         * json types: dictionary, array, string, number, true/false, null
         */

        else {
            // Otherwise the values are not compatible. However, we keep the valid status and we let the IllegalArgumentException to be raised.
        }
    }

    private Method getMotisMethod(String keyword, String name, Class<?> ... paramClass) {
        String uppercaseName = "";

        if (name != null && name.length() > 0)
            uppercaseName = name.substring(0, 1).toUpperCase() + name.substring(1);

        String methodName = keyword + uppercaseName;

        try {
            Method method = this.clazz.getDeclaredMethod(methodName, paramClass);

            if (method.isAnnotationPresent(MotisMethod.class))
                return method;
        } catch (Exception e) {
            // Nothing to do
        }

        return null;
    }

    private void createInstance(Object object, String creationMethodName, MotisValidation motisValidation, Class outClass) {

        JSONObject jsonObject = (JSONObject) motisValidation.getObject();

        boolean valid = true;
        Object newInstance = null; // <-- The new instance to be assigned

        Method creator = this.getMotisMethod(creationMethodName, null, String.class, MotisCreation.class);
        if (creator != null) {
            try {

                MotisCreation motisCreation = new MotisCreation(jsonObject);

                boolean accessible = creator.isAccessible();
                creator.setAccessible(true);
                creator.invoke(object, motisCreation);
                creator.setAccessible(accessible);

                valid = motisCreation.isValid();
                newInstance = motisCreation.getNewObject();
            } catch (Exception e) {
                // If cannot create new instance, invalidate
                valid = false;
            }
        }

        if (newInstance == null && valid == true) {
            // if new instance still not defined and not invalidated yet, lets create it!
            try {
                newInstance = outClass.newInstance();

                // Here the magic of the Motis recursion!
                MotisMapper mapper = new MotisMapper(outClass);
                mapper.map(newInstance, jsonObject);

            } catch (Exception e) {
                // If cannot create new instance, invalidate
                valid = false;
            }
        }

        // Finally, lets set the new instance and the validation state
        motisValidation.setObject(newInstance);
        motisValidation.setValid(valid);
    }

    private void setMotisValidationObject (MotisValidation motisValidation, Object value, boolean isValid) {
        motisValidation.setObject(value);
        motisValidation.setValid(isValid);
    }

}
