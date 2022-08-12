package com.directory.util;

public class ApplicationUtils {
    // Утильные методы
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isEmpty(Long value) {
        return value == null || value == 0;
    }
}