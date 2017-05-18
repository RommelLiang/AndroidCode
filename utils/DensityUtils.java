package com.product.jiamiao.healthbooks.utils;

import android.content.Context;

/**
 * Created by Reinhard Tristan Eugen Heydrich on 2016/11/1 13:25
 */
public class DensityUtils {
	public static int dip2px(float dip, Context ctx) {
		float density = ctx.getResources().getDisplayMetrics().density;
		int px = (int) (dip * density + 0.5f);// 4.9->4, 4.1->4, 四舍五入
		return px;
	}

	public static float px2dip(int px, Context ctx) {
		float density = ctx.getResources().getDisplayMetrics().density;
		float dp = px / density;
		return dp;
	}
}