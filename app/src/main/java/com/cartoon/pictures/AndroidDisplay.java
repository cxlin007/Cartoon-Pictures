package com.cartoon.pictures;

import android.app.Activity;
import android.content.Intent;

import com.cartoon.pictures.business.BDisplay;
import com.catoon.corelibrary.Display;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class AndroidDisplay implements BDisplay {

    private Activity activity;

    public AndroidDisplay(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void showImageDetailActivity(String url) {
        Intent intent = new Intent(activity,ImageDetailActivity.class);
        intent.putExtra("url",url);
        activity.startActivity(intent);
    }
}
