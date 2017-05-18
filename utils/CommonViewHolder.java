package com.product.jiamiao.healthbooks.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sj9 on 2016/12/2.
 */

public class CommonViewHolder {
    private Map<Integer, View> widgets = new HashMap<>();
    public final View convertView;

    private CommonViewHolder(View convertView) {
        this.convertView = convertView;

    }

    //提供获取ViewHolder方法
    public static CommonViewHolder creatCViewHolder(View convertView, int ResId, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), ResId, null);
            CommonViewHolder holder = new CommonViewHolder(convertView);
            //存值
            convertView.setTag(holder);
        }
        return (CommonViewHolder) convertView.getTag();
    }

    //提供存控件的方法
    public void putView(int id, View view) {
        widgets.put(id, view);
    }

    /**
     * 根据id取值，如果集合里面没有先存再取
     */
    public View getView(int id) {
        if (widgets.get(id) == null) {
            widgets.put(id, convertView.findViewById(id));
        }
        return widgets.get(id);
    }

    //根据id取值
    public <T extends View> T getView(int id, Class<T> clazz) {
        return (T) getView(id);
    }

    //获取TextView
    public TextView getTV(int id) {
        return getView(id, TextView.class);
    }

    //获取ImageView
    public ImageView getIV(int id) {
        return getView(id, ImageView.class);
    }
}
