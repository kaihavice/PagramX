package com.xuyazhou.common.util;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 提供与Android系统有关的辅助方法
 */
public class SystemUtil {

    /**
     * 检测一个android程序是否在运行
     *
     * @param context     使用本程序的Activity或Service
     * @param PackageName 指定要检查的android程序的包名
     * @return 如果指定android程序正在运行，返回true
     */
    public static boolean isAppRunning(Context context, String PackageName) {
        boolean isStarted = false;
        try {
            ActivityManager mActivityManager =
                    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<RunningAppProcessInfo> run = mActivityManager.getRunningAppProcesses();
            for (RunningAppProcessInfo pro : run) {
                if (pro.processName.equals(PackageName)) {
                    isStarted = true;
                    break;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return isStarted;
    }

    /**
     * 检测一个Service是否在运行
     *
     * @param context
     * @param serviceClassName 待检测的服务的类名（完全限定名：包名+服务类名）
     * @return 如果指定Service正在运行，返回true
     */
    public static boolean isServiceRunning(Context context, String serviceClassName) {
        ActivityManager manager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClassName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取手机屏幕信息（包括高度、宽度、分辨率等）
     *
     * @param activity
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }


    /** 获取屏幕宽度 */
    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return display.getWidth();
    }

    @SuppressWarnings("deprecation")
    public static int getScreenHeight(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return display.getHeight();
    }

    /**
     * 获取手机状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 关闭系统中不重要的进程
     * 未整理
     * @param context
     */
    public static void killProcess(Context context) {
        ActivityManager activityManger = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> list = activityManger.getRunningAppProcesses();// 得到正在运行的进程信息
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                RunningAppProcessInfo apinfo = list.get(i);
                System.out.println("pid	= " + apinfo.pid);
                System.out.println("processName	= " + apinfo.processName);
                System.out.println("importance	= " + apinfo.importance);// importance
                // 该进程的重要程度
                // 分为几个级别，数值越低就越重要。
                // 数值对应的重要性请看api,我判断是大于ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE的都杀掉，
                // 一般数值大于ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE的进程都长时间没用或者空进程了。
                String[] pkgList = apinfo.pkgList;
                if (apinfo.importance > RunningAppProcessInfo.IMPORTANCE_SERVICE) {
                    for (String aPkgList : pkgList) {// pkgList
                        // 得到该进程下运行的包名
                        // activityManger.restartPackage(pkgList[j]);//2.2版本以下的用activityManger.restartPackage(pkgList[j]);
                        // 对应权限<uses-permission
                        // android:name="android.permission.RESTART_PACKAGES" />
                        if (!aPkgList.equals("com.elephant.yoyo")) {
                            activityManger.killBackgroundProcesses(aPkgList); // 2.2以上,请用killBackgroundProcesses
                            // 对应权限<uses-permission
                            // android:name="android.permission.KILL_BACKGROUND_PROCESSES"
                            // />
                        }
                    }
                    // Process.killProcess(apinfo.pid); //用此种方法的条件
                    // (没有验证过，不知是否是这样)
                    // a、将被杀掉的进程 和 当前进程 处于同一个包或者应用程序中；
                    // b、将被杀掉的进程 是由当前应用程序所创建的附加进程；
                    // c、将被杀掉的进程 和 当前进程 共享了普通用户的UID。（这里的普通用户，是相对于Root权限的用户来说的）
                }
            }
        }
    }


    /** >=2.2 */
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /** >=2.3 */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /** >=3.0 LEVEL:11 */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /** >=3.1 */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /** >=4.0 14 */
    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * >= 4.1 16
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /** >= 4.2 17 */
    public static boolean hasJellyBeanMr1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    /** >= 4.3 18 */
    public static boolean hasJellyBeanMr2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static int getSDKVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    @SuppressWarnings("deprecation")
    public static String getSDKVersion() {
        return Build.VERSION.SDK;
    }
}
