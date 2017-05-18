package com.product.jiamiao.healthbooks.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * Created by Reinhard Tristan Eugen Heydrich on 2016/10/28.
 */
public class ToastUtils {
    private static Context sContext;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static void show(int resId) {
        Toast.makeText(sContext, resId, Toast.LENGTH_SHORT).show();
    }

    public static void show(String text) {
        Toast.makeText(sContext, text, Toast.LENGTH_SHORT).show();
    }
}
