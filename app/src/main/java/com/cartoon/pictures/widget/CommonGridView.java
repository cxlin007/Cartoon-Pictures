package com.cartoon.pictures.widget;

import android.app.Activity;
import android.widget.GridView;

import com.cartoon.pictures.uilibrary.widget.MGridView;
import com.cartoon.pictures.uilibrary.widget.MListView;

/**
 * Created by chenxunlin01 on 2016/6/14.
 */
public class CommonGridView extends CommonAbsListView<GridView> {


    public CommonGridView(Activity activity) {
        super(activity);
    }

    @Override
    protected MListView createListView() {
        return new MGridView(activity);
    }
}
