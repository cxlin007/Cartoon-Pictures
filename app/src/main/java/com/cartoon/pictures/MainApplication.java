package com.cartoon.pictures;

import android.app.Application;

import com.cartoon.pictures.business.controllers.CartoonPicturesController;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class MainApplication extends Application {

    private CartoonPicturesController cartoonPicturesController;

    @Override
    public void onCreate() {
        super.onCreate();

        cartoonPicturesController = new CartoonPicturesController();
    }

    public CartoonPicturesController getCartoonPicturesController() {
        return cartoonPicturesController;
    }
}
