package com.product.jiamiao.healthbooks.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 工具类
 * Created by Reinhard Tristan Eugen Heydrich on 2016/10/28.
 */
public class GlobalUtils {

    public static Bitmap drawable2Bitmap(Context context, int drawable) {
        Resources res = context.getResources();
        return BitmapFactory.decodeResource(res, drawable);
    }


    public static String getSixUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 6);
    }

    public final static String mD5Encoder(String s, String charset) {
        try {
            byte[] btInput = s.getBytes(charset);
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString().toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }

    public final static String mD5Password(String password) {
        password = "liangwenshuo" + password;
        return mD5Encoder(password, "UTF-8");
    }


    public static String getDeviceName() {
        return Build.MODEL;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * * 获取版本号
     * * @return 当前应用的版本号
     */
    public static int getVersion(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

    /**
     * 验证手机号码
     *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,1-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 设备唯一标识码
     *
     * @param context
     * @return
     */
    public static String getDeviceCode(Context context) {
        UUID uuid;
        final String androidId = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID);
        // Use the Android ID unless it's broken, in which case
        // fallback on deviceId,
        // unless it's not available, then fallback on a random
        // number which we store to a prefs file
        try {
            if (!"9774d56d682e549c".equals(androidId)) {
                uuid = UUID.nameUUIDFromBytes(androidId
                        .getBytes("utf8"));
            } else {
                final String deviceId = (
                        (TelephonyManager) context
                                .getSystemService(Context.TELEPHONY_SERVICE))
                        .getDeviceId();
                uuid = deviceId != null ? UUID
                        .nameUUIDFromBytes(deviceId
                                .getBytes("utf8")) : UUID
                        .randomUUID();
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        LogUtils.v(uuid.toString());
        return uuid.toString();
    }

    /**
     * 键盘高度
     *
     * @param paramActivity
     * @return
     */
    public static int getKeyboardHeight(Activity paramActivity) {
        int height = ScreenUtils.getScreenHeight(paramActivity) - ScreenUtils.getStatusBarHeight(paramActivity)
                - ScreenUtils.getAppHeight(paramActivity);
        if (height == 0) {
            height = SPUtils.getIntShareData("KeyboardHeight", 787);//787为默认软键盘高度 基本差不离
        } else {
            SPUtils.putIntShareData("KeyboardHeight", height);
        }
        return height;
    }

    public static String spliceReportUrl(String url, String objectId) {
        url = url.replace("{uid}", SPUtils.getUserId());
        String ukey = GlobalUtils.mD5Encoder(SPUtils.getUserId() + SPUtils.getUserKey(), "utf8");
        url = url.replace("{ukey}", ukey);
        url = url.replace("{objectid}", objectId);
        return url;
    }

    public static String spliceFeaturetUrl(String url) {
        url = url.replace("{uid}", SPUtils.getUserId());
        String ukey = GlobalUtils.mD5Encoder(SPUtils.getUserId() + SPUtils.getUserKey(), "utf8");
        url = url.replace("{ukey}", ukey);
        return url;
    }


    public static DisplayMetrics getScreenDisplayMetrics(Context context) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics;
    }

    public static int getDrawableIdByName(Context context, String drawableName) {

        return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
    }

    private static long lastClickTime;

    /**
     * 快速点击两次
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
