package com.xuyazhou.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.TypedValue;

public class DeviceUtil {
    private final static String PREFS_FILE = "device_info.xml";
    private final static String PREFS_DEVICE_ID = "device_id";
    private final static String PREFS_DEVICE_UUID = "device_uuid";
    private final static String PREFS_DEVICE_MAC = "device_mac";

    private static String deviceUuid = null;
    private static String deviceId = null;
    private static String deviceMac = null;

    /**
     * Returns a unique UUID for the current android device.
     *
     * @return a UUID that may be used to uniquely identify your device for most
     * purposes.
     * @see
     */
    public static String getDeviceUuid(Context context) {
        if (deviceUuid == null) {
            final SharedPreferences prefs = context.getSharedPreferences(
                    PREFS_FILE, 0);
            final String prefUuid = prefs.getString(PREFS_DEVICE_UUID, null);
            if (prefUuid != null) {
                deviceUuid = prefUuid;
            } else {
                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                String tmDevice = "" + tm.getDeviceId();
                String tmSerial = "" + tm.getSimSerialNumber();
                String androidId = ""
                        + android.provider.Settings.Secure.getString(
                        context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
                UUID uuid = new UUID(androidId.hashCode(),
                        ((long) tmDevice.hashCode() << 32)
                                | tmSerial.hashCode());
                deviceUuid = uuid.toString();
                prefs.edit().putString(PREFS_DEVICE_UUID, deviceUuid).commit();
            }
        }
        return deviceUuid;
    }

    /**
     * 获取手机设备ID
     */
    public static String getDeviceId(Context context) {
        if (deviceId == null) {
            final SharedPreferences prefs = context.getSharedPreferences(
                    PREFS_FILE, 0);
            final String prefId = prefs.getString(PREFS_DEVICE_ID, null);
            if (prefId != null) {
                deviceId = prefId;
            } else {
                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                deviceId = tm.getDeviceId();
                prefs.edit().putString(PREFS_DEVICE_ID, deviceId).commit();
            }
        }
        return deviceId;
    }

    /**
     * 获取设备Mac地址
     *
     * @param context
     * @return
     */
    public static String getDeviceMAC(Context context) {
        if (deviceMac == null) {
            final SharedPreferences prefs = context.getSharedPreferences(
                    PREFS_FILE, 0);
            final String prefMac = prefs.getString(PREFS_DEVICE_MAC, null);
            if (prefMac != null) {
                deviceMac = prefMac;
            } else {
                WifiManager wifi = (WifiManager) context
                        .getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifi.getConnectionInfo();
                deviceMac = info.getMacAddress();

                prefs.edit().putString(PREFS_DEVICE_MAC, deviceMac).commit();
            }
        }
        return deviceMac;
    }

    /**
     * 如果外部存储设备可以拔插（比如外置SD卡），返回true；
     * 如果外部存储设备是内嵌在设备中，不能物理拔插，返回false
     */
    public static boolean isExternalStorageRemovable() {
        if (SystemUtil.hasGingerbread())
            return Environment.isExternalStorageRemovable();
        else
            return Environment.MEDIA_REMOVED.equals(Environment.getExternalStorageState());
    }


    /**
     * 获得设备的固件版本号
     */
    public static String getReleaseVersion() {
        return StringUtil.makeSafe(Build.VERSION.RELEASE);
    }

    /**
     * 检测是否是中兴机器
     */
    public static boolean isZte() {
        return getDeviceModel().toLowerCase().indexOf("zte") != -1;
    }

    /**
     * 判断是否是三星的手机
     */
    public static boolean isSamsung() {
        return getManufacturer().toLowerCase().indexOf("samsung") != -1;
    }

    /**
     * 检测是否HTC手机
     */
    public static boolean isHTC() {
        return getManufacturer().toLowerCase().indexOf("htc") != -1;
    }

    /**
     * 检测当前设备是否是特定的设备
     *
     * @param devices
     * @return
     */
    public static boolean isDevice(String... devices) {
        String model = DeviceUtil.getDeviceModel();
        if (devices != null && model != null) {
            for (String device : devices) {
                if (model.indexOf(device) != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获得设备型号
     *
     * @return
     */
    public static String getDeviceModel() {
        return StringUtil.trim(Build.MODEL);
    }

    /**
     * 获取厂商信息
     */
    public static String getManufacturer() {
        return StringUtil.trim(Build.MANUFACTURER);
    }

    /**
     * 判断是否是平板电脑
     *
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 检测是否是平板电脑
     *
     * @param context
     * @return
     */
    public static boolean isHoneycombTablet(Context context) {
        return SystemUtil.hasHoneycomb() && isTablet(context);
    }

    public static int dipToPX(final Context ctx, float dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, ctx.getResources().getDisplayMetrics());
    }

    /**
     * 获取CPU的信息
     *
     * @return
     */
    public static String getCpuInfo() {
        String cpuInfo = "";
        try {
            if (new File("/proc/cpuinfo").exists()) {
                FileReader fr = new FileReader("/proc/cpuinfo");
                BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
                cpuInfo = localBufferedReader.readLine();
                localBufferedReader.close();

                if (cpuInfo != null) {
                    cpuInfo = cpuInfo.split(":")[1].trim().split(" ")[0];
                }
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
        return cpuInfo;
    }

    /**
     * 判断是否支持闪光灯
     */
    public static boolean isSupportCameraLedFlash(PackageManager pm) {
        if (pm != null) {
            FeatureInfo[] features = pm.getSystemAvailableFeatures();
            if (features != null) {
                for (FeatureInfo f : features) {
                    if (f != null && PackageManager.FEATURE_CAMERA_FLASH.equals(f.name)) //判断设备是否支持闪光灯
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * 检测设备是否支持相机
     */
    public static boolean isSupportCameraHardware(Context context) {
        if (context != null && context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public static boolean isArm(){
        if  (Build.CPU_ABI.contains("x86"))
            return false;
        if(Build.CPU_ABI2.contains("x86"))
            return false;
        return true;


    }
}
