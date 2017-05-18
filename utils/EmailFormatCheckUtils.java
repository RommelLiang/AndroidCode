package com.product.jiamiao.healthbooks.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Reinhard Tristan Eugen Heydrich on 2016/11/23 20:29
 * 校验用户输入是否为邮箱
 */
public class EmailFormatCheckUtils {

	public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "#^[a-z_0-9.-]{1,64}@([a-z0-9-]{1,200}.){1,5}[a-z]{1,6}$#i";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
}