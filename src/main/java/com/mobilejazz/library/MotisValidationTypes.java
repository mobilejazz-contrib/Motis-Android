package com.mobilejazz.library;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

    public static Date convertStringToDate(String dateAsString, String format) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        Date localTime = new Date(System.currentTimeMillis());

        Locale locale = java.util.Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        sdf.setTimeZone(tz);

        if (dateAsString != null && !dateAsString.isEmpty()) {
            Date date = null;
            try {
                Date d = sdf.parse(dateAsString);
                date = new Date(d.getTime() + TimeZone.getDefault().getOffset(localTime.getTime()));
                return date;
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


}
