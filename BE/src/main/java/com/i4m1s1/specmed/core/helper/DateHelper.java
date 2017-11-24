package com.i4m1s1.specmed.core.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class DateHelper {
    public static String getCurrentDateAsString(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date today = Calendar.getInstance().getTime();
        String formatedDate = sdf.format(today);
        return formatedDate;
    }

    public static Date getCurrentDate() {
        Date today = Calendar.getInstance().getTime();
        return today;
    }
}
