package com.mobilejazz.library;

/**
 * Created by Jose Luis on 10/10/14.
 */
public class MotisValidationTypes {


    public static String removedAllSpaces (String value) {
        return value.replaceAll("\\s","");
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

}
