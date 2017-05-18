package com.product.jiamiao.healthbooks.utils;


import com.product.jiamiao.healthbooks.BuildConfig;

import com.orhanobut.logger.Logger;

/**
 * Log统一管理类
 * Created by Reinhard Tristan Eugen Heydrich on 2016/10/28.
 */
public class LogUtils {
    private LogUtils()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    private static final String TAG = "sys";

    // 下面四个是默认tag的函数
    public static void i(String msg)
    {
        if (BuildConfig.LOG_DEBUG)
            Logger.i(msg);
    }

    public static void d(String msg)
    {
        if (BuildConfig.LOG_DEBUG)
            Logger.d(msg);
    }

    public static void e(String msg)
    {
        if (BuildConfig.LOG_DEBUG)
            Logger.e(msg);
    }

    public static void v(String msg)
    {
        if (BuildConfig.LOG_DEBUG)
            Logger.v(msg);
    }
    public static void json(String msg)
    {
        if (BuildConfig.LOG_DEBUG)
            Logger.json(msg);
    }
}
