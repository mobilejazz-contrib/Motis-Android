package models;

import com.mobilejazz.library.MotisMethodTypes;
import com.mobilejazz.library.MotisValidation;
import com.mobilejazz.library.annotations.MotisClass;
import com.mobilejazz.library.annotations.MotisKey;
import com.mobilejazz.library.annotations.MotisMethod;
import com.mobilejazz.library.annotations.MotisValidationMethod;

import java.util.Date;

/**
 * Created by Jose Luis on 09/10/14.
 */
//@MotisClass (dateFormat = "yyyy-MM-dd HH:mm:ss.S")
@MotisClass (dateFormat = "yyyy-MM-dd HH:mm:ss")
public class TestObject {

    @MotisKey("int") private int intField;
    @MotisKey("float") private float floatField;
    @MotisKey("double") private double doubleField;
    @MotisKey("boolean") private boolean booleanField;
    @MotisKey("long") private long longField;

    @MotisKey("string") private String stringField;
    @MotisKey("date") private Date dateField;

    @MotisKey("Float") private Float floatClassField;
    @MotisKey("Integer") private Integer integerClassField;
    @MotisKey("Double") private Double doubleClassField;
    @MotisKey("Long") private Long longClassField;
    @MotisKey("Boolean") private Boolean booleanClassField;

    private int ignoredSetter;

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public float getFloatField() {
        return floatField;
    }

    public void setFloatField(float floatField) {
        this.floatField = floatField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    public void setDoubleField(double doubleField) {
        this.doubleField = doubleField;
    }

    public boolean getBooleanField() {
        return booleanField;
    }

    public void setBooleanField(boolean booleanField) {
        this.booleanField = booleanField;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    public Float getFloatClassField() {
        return floatClassField;
    }

    public void setFloatClassField(Float floatClassField) {
        this.floatClassField = floatClassField;
    }

    public Integer getIntegerClassField() {
        return integerClassField;
    }

    public void setIntegerClassField(Integer integerClassField) {
        this.integerClassField = integerClassField;
    }

    public Double getDoubleClassField() {
        return doubleClassField;
    }

    public void setDoubleClassField(Double doubleClassField) {
        this.doubleClassField = doubleClassField;
    }

    public long getLongField() {
        return longField;
    }

    public void setLongField(long longField) {
        this.longField = longField;
    }

    public Long getLongClassField() {
        return longClassField;
    }

    public void setLongClassField(Long longClassField) {
        this.longClassField = longClassField;
    }

    public Boolean getBooleanClassField() {
        return booleanClassField;
    }

    public void setBooleanClassField(Boolean booleanClassField) {
        this.booleanClassField = booleanClassField;
    }


    @MotisMethod(MotisMethodTypes.ON_CREATION) public void motisOnCreation () {

        System.out.println("Call to motisOnCreation with this data;");

    }

    @MotisMethod(MotisMethodTypes.ON_DID_CREATE) public void motisOnDidCreate (String name, Object object) {

        System.out.println("Call to MotisOnDidCreate with this data; Name -> " + name + " and Object -> " + object.toString());

    }

    @MotisMethod (MotisMethodTypes.IGNORED_SETTER) public void motisIgnoredSetter (String jsonValue, String jsonKey) {

        System.out.println("Call to MotisIgnoredSetter with this data; JsonValue -> " + jsonValue + " and jsonKey -> " + jsonKey);

    }

    @MotisMethod (MotisMethodTypes.INVALID_VALUE) public void motisInvalidValue () {

        System.out.println("Call to motisInvalidValue with this data;");

    }


}
