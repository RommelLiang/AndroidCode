package com.product.jiamiao.healthbooks.utils;

import com.product.jiamiao.healthbooks.R;

/**
 * Created by sj9 on 2017/1/18.
 */

public class RepetitionPeriodUtils {

    public static final int[] SPC = {R.drawable.health_reminder_shuimian,R.drawable.health_reminder_chifan,R.drawable.heshui,
            R.drawable.health_reminder_chiyao, R.drawable.health_reminder_jianshen,
            R.drawable.health_reminder_qilaidongdong};


    public static final String[] DATES = {"每天", "周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    public static final String[] TYPES = {"早餐", "午餐", "晚餐"};

    /**
     * 早饭   午饭   晚饭   开始和结束时间
     */
    public static final String[] START_AND_END_TIME = {"08", "30", "12", "30", "18", "30"};

    /**
     * 默认重复周期
     */
    public static final String REMIND_CYCLE = "1111111";


    /**
     * 0睡眠   1早饭   2午饭   3晚饭   4吃药   5    6动动提醒
     */
    public static final int[] REMIND_TYPE_ID = {0,1,2,3,4,5,6};


    /**
     *  将"每日,周一,周二"转换为 "0101111"
     * @param txt
     * @return  num : "1111111"
     */
    public static String txtToNum(String txt){
        String[] split = txt.split(",");
        String s = "";
        String frequency = "";
        if (split[0].equals(DATES[0])) {
            frequency = "1111111";
        }else {
            for (int i = 1; i < DATES.length; i++) {
                for (int i1 = 0; i1 < split.length; i1++) {
                    if (split[i1].equals(DATES[i])){
                        s = "1";
                        break ;
                    }else {
                        s = "0";
                    }
                }
                frequency += s;
            }
        }
        return frequency;
    }


    /**
     *            将"0101111"   转换为    "每日,周一,周二"
     * @param num
     * @return  num : "1111111"
     */
    public static String numToTxt(String num){
        String frequency = "";
        int sum = 0;
        if (num.equals(REMIND_CYCLE)) {
            frequency = DATES[0];
        }else {
            for (int i = 0; i < num.length(); i++) {
                if ('1' == num.charAt(i)){
                    sum++;
                    frequency += DATES[i + 1] + ",";
                }
            }
            frequency = frequency.substring(0, frequency.length() - 1);
        }
        return frequency;
    }
}
