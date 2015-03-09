package com.xuyazhou.common.util;

import java.util.regex.Pattern;

/**
 * Created by dss on 2014/12/4
 */
public class StringUtil {

    private static final String PATTERN_ALPHABETIC_OR_NUMBERIC = "[A-Za-z0-9]*";
    private static final String PATTERN_NUMBERIC = "\\d*\\.{0,1}\\d*";

    /**
     * 合并字符列表
     *
     * @param separator
     * @param data
     * @return
     */
    public static String implode(String separator, String... data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length - 1; i++) {
            //data.length - 1 => to not add separator at the end
            if (!data[i].matches(" *")) {//empty string are ""; " "; "  "; and so on
                sb.append(data[i]);
                sb.append(separator);
            }
        }
        sb.append(data[data.length - 1].trim());
        return sb.toString();
    }

    /**
     * 字符串是否由字面或数字组成
     *
     * @param str
     * @return
     */
    public static boolean isAlphabeticOrNumberic(String str) {
        return Pattern.compile(PATTERN_ALPHABETIC_OR_NUMBERIC).matcher(str).matches();
    }

    /**
     * 字符串是否是数组
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        return Pattern.compile(PATTERN_NUMBERIC).matcher(str).matches();
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * 判断对象是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(final Object str) {
        return (str == null || str.toString().length() == 0);
    }

    /**
     * 判断一组字符串是否有一个为空
     *
     * @param strs
     * @return
     */
    public static boolean isNullOrEmpty(final String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (str == null || str.length() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断子字符串是否有出现在指定字符串中
     *
     * @param str
     * @param c
     * @return
     */
    public static boolean find(String str, String c) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return str.indexOf(c) > -1;
    }

    public static boolean findIgnoreCase(String str, String c) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return str.toLowerCase().indexOf(c.toLowerCase()) > -1;
    }

    /**
     * 比较两个字符串是否相
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == str2)
            return true;

        if (str1 == null)
            str1 = "";
        return str1.equals(str2);
    }


    /**
     * 拼接字符串
     *
     * @param strs
     * @return
     */
    public static String concat(String... strs) {
        StringBuffer result = new StringBuffer();
        if (strs != null) {
            for (String str : strs) {
                if (str != null)
                    result.append(str);
            }
        }
        return result.toString();
    }

    /**
     * Helper function for making null strings safe for comparisons, etc.
     *
     * @return (s == null) ? "" : s;
     */
    public static String makeSafe(String s) {
        return (s == null) ? "" : s;
    }

    public static final String EMPTY = "";

    /**
     * 去除字符串首部和尾部的空白字符，返回处理后字符串
     *
     * @param str
     */
    public static String trim(String str) {
        return str == null ? EMPTY : str.trim();
    }
}
