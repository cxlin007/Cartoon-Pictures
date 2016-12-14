package com.cartoon.pictures.business;

import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.business.bean.EmotionInfo;
import com.cartoon.pictures.business.bean.GifInfo;
import com.catoon.corelibrary.Display;

/**
 * Created by chenxunlin01 on 2016/11/23.
 */
public interface BDisplay extends Display {

    public void showGifDialogActivity(GifInfo gifInfo);

    public void showCategoryActivity(CardInfo cardInfo);

    public void showEmotionActivity(CardInfo cardInfo);

    public void showSuEmotionActivity(EmotionInfo emotionInfo);
}
