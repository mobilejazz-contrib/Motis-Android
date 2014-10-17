package models;

import com.mobilejazz.library.MotisCreation;
import com.mobilejazz.library.MotisInterface;
import com.mobilejazz.library.annotations.MotisClass;
import com.mobilejazz.library.annotations.MotisKey;

import java.util.Date;

/**
 * Created by Jose Luis on 09/10/14.
 */
//@MotisClass (dateFormat = "yyyy-MM-dd HH:mm:ss.S")
@MotisClass (dateFormat = "yyyy-MM-dd HH:mm:ss")
public class TestObject implements MotisInterface{

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

    @Override public void motisOnCreation (String fieldName, MotisCreation motisCreation) {
        System.out.println("Will create object for field name <" + fieldName + ">");

    }

    @Override public void motisOnDidCreate(String fieldName, Object newObject) {
        System.out.println("Did create object for field name <" + fieldName + ">, object : " + newObject.toString());

    }

    @Override public void motisIgnoredSetter(String jsonKey, Object jsonValue) {
        System.out.println("Ignored jsonKey <" + jsonKey + "> with jsonValue -> " + jsonValue);

    }

    @Override public void motisInvalidValue(String fieldName, Object jsonValue) {
        System.out.println("Invalid value for field name <" + fieldName + ">, value : " + jsonValue.toString());

    }

}
