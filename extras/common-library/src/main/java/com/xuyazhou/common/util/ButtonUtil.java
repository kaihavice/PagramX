package com.xuyazhou.common.util;

import java.util.HashMap;

/**
 * Created by howe on 15/1/16.
 * Email:howejee@gmail.com
 */
public class ButtonUtil {

    private static final int TIME_LIME = 300;

    public static HashMap<Integer,Long> lastClickTimes = new HashMap<>();

    public static boolean canClickable(int idRes){
        if(lastClickTimes.containsKey(idRes)){
            long lastTime = lastClickTimes.get(idRes);
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastTime>TIME_LIME){
                lastClickTimes.put(idRes,currentTime);
                return true;
            }else{
                return false;
            }
        }else{
            lastClickTimes.put(idRes, System.currentTimeMillis());
            return true;
        }
    }
}
