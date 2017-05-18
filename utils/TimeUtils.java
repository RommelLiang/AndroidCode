package com.product.jiamiao.healthbooks.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Reinhard Tristan Eugen Heydrich on 2016/12/12 15:20
 */
public class TimeUtils {

	public static String getTime() {
		return String.valueOf(System.currentTimeMillis());
	}

	public static String TimeStampDate(String timestampString){
		Long timestamp = Long.parseLong(timestampString);
		String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(timestamp));
		return date;
	}

	public static String getDateTimeStr(Long timestamp){

		String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));
		return date;
	}

	public static String getData(String timestampString){
		Long timestamp = Long.parseLong(timestampString);
		String date = new java.text.SimpleDateFormat("MM月dd日").format(new java.util.Date(timestamp));
		return date;
	}
	public static String getAllData(String timestampString){
		Long timestamp = Long.parseLong(timestampString);
		String date = new java.text.SimpleDateFormat("MM月dd日 HH:mm").format(new java.util.Date(timestamp));
		return date;
	}


	/*将字符串转为时间戳*/
	@RequiresApi(api = Build.VERSION_CODES.N)
	public static long getStringToDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try{
			date = sdf.parse(time);
			} catch(Exception e) {
			e.printStackTrace();
			}
		return date.getTime();
		}

	/**
	 * 08:30
	 * @param time
	 * @return
     */
	@RequiresApi(api = Build.VERSION_CODES.N)
	public static long getRemainingTime(String time){
		String[] split = time.split(":");
		String s = TimeUtils.TimeStampDate(TimeUtils.getTime());
		s =  s + " " +  split[0] + ":" + split[1] + ":" + "00";
		long stringToDate = getStringToDate(s);
		long sum = stringToDate - System.currentTimeMillis();
		return sum;
	}
}