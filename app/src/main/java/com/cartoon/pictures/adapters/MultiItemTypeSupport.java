package com.cartoon.pictures.adapters;

/**
 * Created by chenxunlin01 on 2016/6/1.
 */
public interface MultiItemTypeSupport<T> {

    public int getLayoutId(int itemType);

    public int getItemViewType(int position, T t);

    public int getViewTypeCount();
}
