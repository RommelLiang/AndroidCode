package com.product.jiamiao.healthbooks.utils;

import android.os.Environment;

import java.io.File;

import static com.product.jiamiao.healthbooks.utils.Constant.SD_PATH;

/**
 * Created by Reinhard Tristan Eugen Heydrich on 2016/12/9 17:34
 */
public class Configs {
	public static final String tokenKey = "589AA72C";
	public static final int platform = 1;

	/**
	 * 检查加秒目录是否存在
	 */
	public static void checkDirectoryThere(){
		File sd = Environment.getExternalStorageDirectory();
		String path = sd.getPath() + SD_PATH;
		File file = new File(path);
		if(!file.exists())
			file.mkdir();
	}
}