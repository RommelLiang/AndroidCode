package com.product.jiamiao.healthbooks.utils;

import java.util.Map;

/**
 * Created by Reinhard Tristan Eugen Heydrich on 2016/12/16 16:13
 */
public class LogRequestMap {
	public static void log(Map<String,String> map) {
			LogUtils.e(map.toString() + "``````````````````````````");
	}
}