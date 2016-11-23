package com.cartoon.pictures;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cartoon.pictures.base.BaseActivity;
import com.catoon.corelibrary.controllers.BaseUiController;

/**
 * Created by chenxunlin01 on 2016/11/22.
 */
public class DemoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        imageView.setId(R.id.root_layout);
        setContentView(imageView);
        Glide.with(getApplicationContext()).load("http://www.jcodecraeer.com/uploads/20150327/1427445294704430.png").fitCenter()
                .into(imageView);
    }
}
