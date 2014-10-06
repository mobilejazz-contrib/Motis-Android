package com.mobilejazz.library;


import org.json.JSONObject;

public class MotisCreation {

    private JSONObject jsonObject;
    private boolean valid;
    private Object newObject;

    protected MotisCreation(JSONObject jsonObject)
    {
        super();

        this.jsonObject = jsonObject;
        this.valid = true;
        this.newObject = null;
    }

    protected void setJsonObject(JSONObject jsonObject) { this.jsonObject = jsonObject; }
    public void setValid(boolean valid) { this.valid = valid; }
    public void setNewObject(Object newObject) { this.newObject = newObject; }

    public JSONObject getJsonObject() { return jsonObject; }
    public boolean isValid() { return valid; }
    public Object getNewObject() { return newObject; }
}
