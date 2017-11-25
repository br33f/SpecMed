package com.i4m1s1.specmed.core.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class DateHelper {
    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Europe/Poland");

    public static String getCurrentDateAsString(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date today = getCurrentDate();
        return sdf.format(today);
    }

    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        return calendar.getTime();
    }

    public static long getCurrentDateAsLong() {
        return getCurrentDate().getTime();
    }

    public static Date getDateFromString(String dateAsString, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateAsString);
    }
}
