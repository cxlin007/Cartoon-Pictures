package com.cartoon.pictures.business;

import com.cartoon.pictures.business.state.CartoonPicturesState;
import com.catoon.corelibrary.EvnManager;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class BusinessManager extends EvnManager {

    private static CartoonPicturesState cartoonPicturesState;

    synchronized public static CartoonPicturesState getCartoonPicturesState() {
        if (cartoonPicturesState == null) {
            cartoonPicturesState = new CartoonPicturesState();
        }

        return cartoonPicturesState;
    }
}
