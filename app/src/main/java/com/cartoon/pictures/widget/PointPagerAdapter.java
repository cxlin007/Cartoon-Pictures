package com.cartoon.pictures.widget;

import android.support.v4.view.PagerAdapter;

import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/28.
 */
public abstract class PointPagerAdapter<T> extends PagerAdapter {

    private OnDataChangeLinstener onDataChangeLinstener;
    protected List<T> datas;

    public void setData(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
        if (onDataChangeLinstener != null) {
            onDataChangeLinstener.onDateChange();
        }
    }

    public void setOnDataChangeLinstener(OnDataChangeLinstener onDataChangeLinstener) {
        this.onDataChangeLinstener = onDataChangeLinstener;
    }

    public interface OnDataChangeLinstener {
        public void onDateChange();
    }
}
