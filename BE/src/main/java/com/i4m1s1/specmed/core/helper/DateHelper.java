package com.i4m1s1.specmed.core.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Klasa posiadająca metody do działania na datach i czasie
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public class DateHelper {
    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Europe/Poland");

    /**
     * Zwraca aktualną datę jako String zgodnie z formate
     * @param format format Daty {@see java.text.SimpleDateFormat}
     * @return
     */
    public static String getCurrentDateAsString(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date today = getCurrentDate();
        return sdf.format(today);
    }

    /**
     * Zwraca aktualną datę jako typ Date
     * @return {@see java.util.Date}
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        return calendar.getTime();
    }

    /**
     * Zwraca aktualną datę jako {@see Long}
     * @return czas jako Long
     */
    public static long getCurrentDateAsLong() {
        return getCurrentDate().getTime();
    }

    /**
     * Zwraca datę na podstawie Stringa i formatu
     * @param dateAsString data w stringu
     * @param format format jako {@link java.text.SimpleDateFormat}
     * @return Data jako {@link java.util.Date}
     * @throws ParseException
     */
    public static Date getDateFromString(String dateAsString, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateAsString);
    }
}
