package models;

import com.mobilejazz.library.annotations.MotisClass;
import com.mobilejazz.library.annotations.MotisKey;

import java.util.Date;

/**
 * Created by Jose Luis on 09/10/14.
 */
@MotisClass
public class TestObject {

    @MotisKey("int") private int intField;
    @MotisKey("float") private float floatField;
    @MotisKey("double") private double doubleField;
    @MotisKey("boolean") private boolean booleanField;

    @MotisKey("string") private String stringField;
    @MotisKey("date") private Date dateField;

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

    public boolean isBooleanField() {
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
}
