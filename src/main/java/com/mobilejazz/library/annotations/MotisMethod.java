package com.mobilejazz.library.annotations;

import com.mobilejazz.library.MotisMethodTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;




@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MotisMethod {
    public int value () default MotisMethodTypes.DEFAULT;
}
