package com.cartoon.pictures.business;

import com.cartoon.pictures.business.bean.GifInfo;
import com.catoon.corelibrary.Display;

/**
 * Created by chenxunlin01 on 2016/11/23.
 */
public interface BDisplay extends Display {

    public void showImageDetailActivity(String url);

    public void showGifDialogActivity(GifInfo gifInfo);
}
