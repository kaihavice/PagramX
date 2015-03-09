package com.xuyazhou.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.SpannableString;

/**
 * Created by yazhou on 2015/3/8 0008.
 */
public  class StringsUtils {

	public static String HttpToHttps(String str) {
		SpannableString s = new SpannableString(str);

		Pattern p = Pattern.compile("http://");

		Matcher m = p.matcher(s);

		while (m.find()) {
			int start = m.start();
			int end = m.end();

			return "https://" + str.substring(end, str.length());
		}
		return str;
	}

}
