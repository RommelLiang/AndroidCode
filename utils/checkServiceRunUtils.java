package com.product.jiamiao.healthbooks.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by sj9 on 2016/12/15.
 */

public class checkServiceRunUtils {

    public static boolean isServiceRunning(String serviceClassName , Context context){

        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : services) {
            if (runningServiceInfo.service.getClassName().equals(serviceClassName)){
                return true;
            }
        }
        return false;
    }
}
