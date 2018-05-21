package com.gayson.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jixunzhen on 2018/5/7.
 */
public class DateUtils {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    public static String dateToISOFormat(Date time) {
        return dateFormat.format(time);
    }
}
