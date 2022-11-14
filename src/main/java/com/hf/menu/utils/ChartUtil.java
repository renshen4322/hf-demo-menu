package com.hf.menu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChartUtil {
    public static boolean isValidNaming(String name) {
        String regx = "^[a-zA-Z_0-9.$-]+$";
        if (name.substring(0, 1).equals(".")) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(regx);
            Matcher matcher = pattern.matcher(name);
            return matcher.find();
        }

    }
}
