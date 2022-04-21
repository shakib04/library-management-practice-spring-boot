package com.brac.its.libraryManagement.util;

import java.nio.charset.Charset;
import java.util.Random;

public class StringUtil {

    public static String getRandomString(int length){
        if (length < 1){
            length = 1;
        }
        byte[] array = new byte[length]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
}
