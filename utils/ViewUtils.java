package com.product.jiamiao.healthbooks.utils;

import android.view.View;


/**
 * 视图工具类
 * Created by Reinhard Tristan Eugen Heydrich on 2016/10/28.
 */
public class ViewUtils {
    /**
     * @param loadSuccess 加载成功的视图,可以传空
     * @param loading
     * @param empty
     * @param loadFail
     * @param state
     */
    public static void changeViewState(View loadSuccess, View loading, View empty, View loadFail, LoadStateEnum state) {
        switch (state) {
            case LOADING:
                if (loadSuccess != null)
                    loadSuccess.setVisibility(View.GONE);
                if (loading != null)
                    loading.setVisibility(View.VISIBLE);
                if (empty != null)
                    empty.setVisibility(View.GONE);
                if (loadFail != null)
                    loadFail.setVisibility(View.GONE);
                break;
            case LOAD_SUCCESS:
                if (loadSuccess != null)
                    loadSuccess.setVisibility(View.VISIBLE);
                if (loading != null)
                    loading.setVisibility(View.GONE);
                if (empty != null)
                    empty.setVisibility(View.GONE);
                if (loadFail != null)
                    loadFail.setVisibility(View.GONE);
                break;
            case LOAD_EMPTY:
                if (loadSuccess != null)
                    loadSuccess.setVisibility(View.GONE);
                if (loading != null)
                    loading.setVisibility(View.GONE);
                if (empty != null)
                    empty.setVisibility(View.VISIBLE);
                if (loadFail != null)
                    loadFail.setVisibility(View.GONE);
                break;
            case LOAD_FAIL:
                if (loadSuccess != null)
                    loadSuccess.setVisibility(View.GONE);
                if (loading != null)
                    loading.setVisibility(View.GONE);
                if (empty != null)
                    empty.setVisibility(View.GONE);
                if (loadFail != null)
                    loadFail.setVisibility(View.VISIBLE);
                break;
        }
    }
    /**
     * 加载状态
     * Created by kimxu on 2016/7/3.
     */
    public enum LoadStateEnum {
        LOADING,// 加载中
        LOAD_SUCCESS,// 加载成功
        LOAD_FAIL,// 加载失败
        LOAD_EMPTY// 加载失败
    }
}
