package com.cartoon.pictures;

import android.app.Activity;

import com.catoon.corelibrary.Display;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class AndroidDisplay implements Display {

    private Activity activity;

    public AndroidDisplay(Activity activity) {
        this.activity = activity;
    }
}
