package com.deliverengine.user.util;

import java.util.Random;
import java.util.stream.Collectors;

public class Utils {

    private static final int DEFAULT_STRING_LENGTH = 10;

    public static String randomString(Integer size, Boolean lowercase) {
        String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String output = new Random().ints(size, 0, source.length())
            .mapToObj(it -> String.valueOf(source.charAt(it)))
            .collect(Collectors.joining(""));
        if (lowercase)
            return output.toLowerCase();
        return output;
    }

    public static String randomString() {
        String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return new Random().ints(DEFAULT_STRING_LENGTH, 0, source.length())
            .mapToObj(it -> String.valueOf(source.charAt(it)))
            .collect(Collectors.joining(""));
    }
}