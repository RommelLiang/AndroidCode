package com.product.jiamiao.healthbooks.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Reinhard Tristan Eugen Heydrich on 2016/12/6 20:12
 */
public class WeekDataUtils {
	private SimpleDateFormat sdf;

	public WeekDataUtils(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	/**
	 * 得到本周周一
	 * @return yyyy-MM-dd
	 */
	public String getMon() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		LogUtils.e(sdf.format(c.getTime()));
		return sdf.format(c.getTime());
	}
	/**
	 * 得到本周周二
	 * @return yyyy-MM-dd
	 */
	public String getTue() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 2);
		LogUtils.e(sdf.format(c.getTime()));
		return sdf.format(c.getTime());
	}
	/**
	 * 得到本周周三
	 * @return yyyy-MM-dd
	 */
	public String getWed() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 3);
		LogUtils.e(sdf.format(c.getTime()));
		return sdf.format(c.getTime());
	}
	/**
	 * 得到本周周四
	 * @return yyyy-MM-dd
	 */
	public String getThu() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 4);
		LogUtils.e(sdf.format(c.getTime()));
		return sdf.format(c.getTime());
	}
	/**
	 * 得到本周周五
	 * @return yyyy-MM-dd
	 */
	public String getFri() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 5);
		LogUtils.e(sdf.format(c.getTime()));
		return sdf.format(c.getTime());
	}
	/**
	 * 得到本周周六
	 * @return yyyy-MM-dd
	 */
	public String getSat() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 6);
		LogUtils.e(sdf.format(c.getTime()));
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本周周日
	 *
	 * @return yyyy-MM-dd
	 */
	public String getSunday() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		LogUtils.e(sdf.format(c.getTime()));
		return sdf.format(c.getTime());
	}
}