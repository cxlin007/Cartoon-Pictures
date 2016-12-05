package com.cartoon.pictures.widget;

import android.app.Activity;
import android.widget.ListView;

import com.cartoon.pictures.uilibrary.widget.MListView;

/**
 * Created by chenxunlin01 on 2016/6/14.
 */
public class CommonListView extends CommonAbsListView<ListView> {

    public CommonListView(Activity activity) {
        super(activity);
    }

    @Override
    protected MListView createListView() {
        return new MListView(activity);
    }

}
