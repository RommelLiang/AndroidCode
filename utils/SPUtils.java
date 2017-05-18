package com.product.jiamiao.healthbooks.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * SharedPreferences封装类
 * Created by Reinhard Tristan Eugen Heydrich on 2016/10/28.
 */
public class SPUtils {
    /**
     * 保存在手机里面的文件名
     */
    public static final String IS_FIRST = "isFirst";
    public static final String USER_INFO_EDIT_END = "userInfoEditEnd";
    public static final String IS_SCORE_SHOW = "is_score_show";
    public static final String USER_WEI_XIN_INFO_EDIT_END = "userWeiXinInfoEditEnd";
    public static final String USER_GUIDE_END = "userGuideEnd";
    public static final String WIDTH = "width";
    public static final String DPI = "dpi";
    public static final String FILE_NAME = "sys_data";
    public static final String USER_SEX = "user_sex";
    public static final String USER_PHOTO = "user_photo";
    private static final String USER_PHONE = "user_phone";
    private static final String USER_PWD = "user_pwd";
    private static final String UPDATE_VERSION = "update_version";
    private static final String USER_SAFEKEY = "user_safeKey";
    private static final String USER_MEMBER_ID = "user_member_id";
    public static final String SHOW_EVENT = "show_event";
    public static final String HOME_SEEK_BAR = "seek_bar";
    //private static final String PLAY_MODE = "play_mode";
    public static final String IS_LOGIN = "is_login";


    /**
     * 睡眠提醒
     */
    public static final String SLEEP_REMIND_START_TIME_HOURS = "sleepRemindStartTimeHours";
    public static final String SLEEP_REMIND_START_TIME_MIN = "sleepRemindStartTimeMin";
    public static final String SLEEP_REMIND_STOP_TIME_HOURS = "sleepRemindStopTimeHours";
    public static final String SLEEP_REMIND_STOP_TIME_MIN = "sleepRemindStopTimeMin";
    public static final String SLEEP_REMIND_SWITCH_STATE = "sleepRemindSwitchState";

    /**
     * 动动提醒
     */
    public static final String MOVEMENT_IS_REMIND = "movement_is_remind";
    public static final String MOVEMENT_START_TIME = "movement_start_time";
    public static final String MOVEMENT_STOP_TIME = "movement_stop_time";
    public static final String MOVEMENT_TIME_INTERVAL = "movement_time_interval";
    public static final String MOVEMENT_REPEAT_TIME_INTERVAL = "movement_repeat_time_interval";

    /**
     * 吃饭提醒之早餐
     */
    public static final String BREAKFAST_REMIND_START_TIME = "breakfast_remind_start_time";
    public static final String BREAKFAST_REMIND_STOP_TIME = "breakfast_remind_stop_time";
    public static final String BREAKFAST_REMIND_CYCLE = "breakfast_remind_cycle";
    public static final String BREAKFAST_REMIND_TYPE = "breakfast_remind_type";
    public static final String BREAKFAST_REMIND_SWITCH = "breakfast_remind_switch";

    /**
     * 吃饭提醒之午餐
     */
    public static final String BREAKFAST_LUNCH_START_TIME = "breakfast_lunch_start_time";
    public static final String BREAKFAST_LUNCH_STOP_TIME = "breakfast_lunch_stop_time";
    public static final String BREAKFAST_LUNCH_CYCLE = "breakfast_lunch_cycle";
    public static final String BREAKFAST_LUNCH_TYPE = "breakfast_lunch_type";
    public static final String BREAKFAST_LUNCH_SWITCH = "breakfast_lunch_switch";


    /**
     * 吃饭提醒之晚餐
     */
    public static final String BREAKFAST_DINNER_START_TIME = "breakfast_dinner_start_time";
    public static final String BREAKFAST_DINNER_STOP_TIME = "breakfast_dinner_stop_time";
    public static final String BREAKFAST_DINNER_CYCLE = "breakfast_dinner_cycle";
    public static final String BREAKFAST_DINNER_TYPE = "breakfast_dinner_type";
    public static final String BREAKFAST_DINNER_SWITCH = "breakfast_dinner_switch";




    public static final String Fragment_Left ="Fragment_left";
    public static final String Fragment_Top = "Fragment_top";
    public static final String Fragment_Right ="Fragment_right";
    public static final String Fragment_Bottom = "Fragment_bottom";
    public static final String USER_Location= "USER_Location";
    public static final String USER_WISH= "USER_WISH";
    public static final String ALL_WISH = "ALL_WISH";
    private static Context mContext;

    private static String USER_ID = "userId";
    private static String USER_KEY = "userkey";
    public static final String USER_NAME = "username";
    private static String CLIENT_TYPE = "clienttype";
    private static String CLIENT = "client";
    private static String DEVICE_CODE = "devicecode";
    private static String VERSION = "version";
    private static String UN_READ_MESSAGE = "un_read_message";
    public static String DB_INIT = "db_init";

    public static void init(Context context) {
        mContext = context;
    }

    public static void saveSeekBarWeith(int w) {
        put(HOME_SEEK_BAR,w);
    }

    public static int getSeekBarWeith(){
        return (int) get(HOME_SEEK_BAR,180);
    }
    public static String getUserId() {
        return (String) get(USER_ID, "");
    }

    public static void saveUserId(String userId) {
        put(USER_ID, userId);
    }

    public static String getUserKey() {
        return (String) get(USER_KEY, "");
    }

    public static void saveUserKey(String userKey) {
        put(USER_KEY, userKey);
    }

    public static void saveUserName(String userName) {
        put(USER_NAME, userName);
    }

    public static String getUserName() {
        return (String) get(USER_NAME, "");
    }

    public static void saveUserSex(int userSex) {
        put(USER_SEX, userSex);
    }

    public static int getDpi() {
        return (int) get(DPI,480);
    }
    public static int getWidth() {
        return (int) get(WIDTH,1080);
    }

    public static void saveDPI(int dpi) {
        put(DPI,dpi);
    }
    public static void saveWidth(int width) {
        put(WIDTH,width);
    }

    public static int getUserSex() {
        return (int) get(USER_SEX, 1);
    }

    public static void saveUserPhone(String userPhone) {
        put(USER_PHONE, userPhone);
    }

    public static void saveFragmentTop(String top) {
        put(Fragment_Top,top);
    }
    public static void saveFragmentLeft(String left) {
        put(Fragment_Left,left);
    }
    public static void saveFragmentRight(String right) {
        put(Fragment_Right,right);
    }
    public static void saveFragmentBottom(String bottom) {
        put(Fragment_Bottom,bottom);
    }


    public static String getUserPhone() {
        return (String) get(USER_PHONE, "0");
    }

    public static String getTop() {
        return (String) get(Fragment_Top, "0");
    }
    public static String getLeft() {
        return (String) get(Fragment_Left, "0");
    }
    public static String getRight() {
        return (String) get(Fragment_Right, "0");
    }
    public static String getBottem() {
        return (String) get(Fragment_Bottom, "0");
    }

    public static void saveUserPwd(String userPwd) {
        put(USER_PWD,userPwd);
    }
    public static String getUserPwd(){
        return (String) get(USER_PWD,"");
    }

    public static void saveUserMemberId(String userMemberId){
        put(USER_MEMBER_ID,userMemberId);
    }

    public static String getUserMemberId() {
        return (String) get(USER_MEMBER_ID,"");
    }

    public static void saveUserSafeKey(String userSafeKey) {
        put(USER_SAFEKEY,userSafeKey);
    }

    public static String getUserSafekey() {
        return (String) get(USER_SAFEKEY,"");
    }

    public static void saveUserPhoto(String userPhoto) {
        put(USER_PHOTO, userPhoto);
    }

    public static String getUserPhoto() {
        return (String) get(USER_PHOTO, "");
    }

    public static String getClientType() {
        return (String) get(CLIENT_TYPE, "");
    }

    public static void saveClientType(String clientType) {
        put(CLIENT_TYPE, clientType);
    }

    public static String getClient() {
        return (String) get(CLIENT, "");
    }

    public static void saveClient(String client) {
        put(CLIENT, client);
    }

    public static String getDeviceCode() {
        return (String) get(DEVICE_CODE, "");
    }

    public static void saveDeviceCode(String deviceCode) {
        put(DEVICE_CODE, deviceCode);
    }

    public static String getVersion() {
        return (String) get(VERSION, "");
    }

    public static void saveVersion(String version) {
        put(VERSION, version);
    }
    public static void saveUpdateVersion(int version) {
        put(UPDATE_VERSION, version);
    }
    public static int getUpdateVersion() {
        return (int) get(UPDATE_VERSION, -1);
    }


    public static void saveUnReadMsg(boolean unRead) {
        put(UN_READ_MESSAGE, unRead);
    }


    public static boolean getUnReadMsg() {
        return (boolean) get(UN_READ_MESSAGE, false);
    }

    public static String getUSER_Location(){
        return (String) get(USER_Location,"北京");
    }

    public static void saveUSER_Location(String location) {
        put(USER_Location,location);
    }

    public static String getUSER_WISH(){
        return (String) get(USER_WISH,"");
    }

    public static void saveUSER_WISH(String wish) {
        put(USER_WISH,wish);
    }

    public static String getALL_WISH(){
        return (String) get(ALL_WISH,"");
    }

    public static void saveALL_WISH(String wishAll) {
        put(ALL_WISH,wishAll);
    }




    //数据库初始化
    public static boolean dbInit() {
        return (boolean) get(DB_INIT, true);
    }

    public static void setDbInit() {
        put(DB_INIT, false);
    }


    public static int getIntShareData(String keyboardHeight, int height) {
        return (int) get(keyboardHeight, height);
    }

    public static void putIntShareData(String keyboardHeight, int height) {
        put(keyboardHeight, height);
    }

    public static boolean hasLogin() {
        if (TextUtils.isEmpty(getUserId())) {
            return false;
        } else {
            return true;
        }
    }

    public static void logOut(){
        remove(USER_ID);
        remove(USER_NAME);
        remove(USER_SEX);
        remove(USER_PHOTO);
        remove(USER_KEY);
        remove(USER_PHONE);
    }
    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(String key, Object defaultObject) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }




    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}
