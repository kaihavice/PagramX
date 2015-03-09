package com.xuyazhou.common.util;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by howe on 15/1/14.
 * Email:howejee@gmail.com
 */
public class UrlUtil {
    public static String assembleUrlByGet(String url, Map<String, String> params) {

        StringBuilder sb = new StringBuilder(url);
        if (params!=null && !params.isEmpty()) {
            if (!url.contains("?")) {
                sb.append("?");
            } else {
                sb.append('&');
            }
            int count = 0;
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                count++;
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                if (count > 1) {
                    sb.append("&");
                }
                sb.append(key).append("=").append(value);
            }
        }
        return sb.toString();

    }
}
