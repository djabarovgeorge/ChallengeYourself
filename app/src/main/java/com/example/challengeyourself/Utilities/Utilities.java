package com.example.challengeyourself.Utilities;

import java.util.Calendar;

public class Utilities {
    public static boolean isNull(Calendar param) {
        return param == null;
    }
    public static boolean isNullOrBlank(String param) {
        return param == null || param.trim().length() == 0;
    }
    public static boolean CompareContent(String param1, String param2) {
        return param1.compareToIgnoreCase(param2) == 0;
    }
    public static boolean ContainsContent(String source, String ifIn) {
        return source.toLowerCase().contains(ifIn.toLowerCase());
    }
    public static boolean IsNullOrEmpty(String source) {
        return source == null || source.length() == 0;
    }
}