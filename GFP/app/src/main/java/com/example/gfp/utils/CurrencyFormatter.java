package com.example.gfp.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {

    public static String format(double amount, String currencySymbol) {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);

        return currencySymbol + " " + format.format(amount);
    }
}