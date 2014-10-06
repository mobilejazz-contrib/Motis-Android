package com.mobilejazz.library;

/**
 * Created by martinhj on 15/07/14.
 */
public class MotisValidation {

    protected MotisValidation(Object object)
    {
        super();

        this.object = object;
        this.valid = true;
    }

    private Object object;
    private boolean valid;

    public void setObject(Object object) { this.object = object; }
    public void setValid(boolean valid) { this.valid = valid; }

    public Object getObject() { return object; }
    public boolean isValid() { return valid; }
}
