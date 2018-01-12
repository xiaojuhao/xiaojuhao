package com.xjh.support.excel.wirte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static boolean isNumber(String nums) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(nums);
        return m.matches();
    }

    public static boolean isBigNumber(String nums) {
        if (nums == null || nums.equals("") || nums.length() < 7) {
            return false;
        }
        return isNumber(nums);
    }
   
}
