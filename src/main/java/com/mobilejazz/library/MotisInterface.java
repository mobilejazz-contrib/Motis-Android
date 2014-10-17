package com.mobilejazz.library;

import com.mobilejazz.library.annotations.MotisMethod;

/**
 * Created by Jose Luis on 17/10/14.
 */
public interface MotisInterface {

    public void motisOnCreation (String fieldName, MotisCreation motisCreation);
    public void motisOnDidCreate (String fieldName, Object newObject);
    public void motisIgnoredSetter (String jsonKey, Object jsonValue);
    public void motisInvalidValue (String fieldName, Object jsonValue);
}
