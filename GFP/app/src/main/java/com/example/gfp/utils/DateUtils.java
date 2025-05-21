package com.example.gfp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {


    public static String formatDateToShortString(Date date) {
        if (date == null) return "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM", Locale.getDefault());
        return sdf.format(date);
    }


    public static String formatDateToLongString(Date date) {
        if (date == null) return "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        return sdf.format(date);
    }
}