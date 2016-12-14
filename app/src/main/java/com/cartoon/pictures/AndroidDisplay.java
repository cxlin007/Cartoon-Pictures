package com.cartoon.pictures;

import android.app.Activity;
import android.content.Intent;

import com.cartoon.pictures.activities.CategoryActivity;
import com.cartoon.pictures.activities.EmotionActivity;
import com.cartoon.pictures.activities.GifDialogActivity;
import com.cartoon.pictures.activities.SuEmotionActivity;
import com.cartoon.pictures.business.BDisplay;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.EmotionInfo;
import com.cartoon.pictures.business.bean.GifInfo;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class AndroidDisplay implements BDisplay {

    private Activity activity;

    public AndroidDisplay(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void showGifDialogActivity(GifInfo gifInfo) {
        Intent intent = new Intent(activity,GifDialogActivity.class);
        intent.putExtra("gif",gifInfo);
        activity.startActivity(intent);
    }

    @Override
    public void showCategoryActivity(CardInfo cardInfo) {
        Intent intent = new Intent(activity,CategoryActivity.class);
        intent.putExtra("info",cardInfo);
        activity.startActivity(intent);
    }

    @Override
    public void showEmotionActivity(CardInfo cardInfo) {
        Intent intent = new Intent(activity,EmotionActivity.class);
        intent.putExtra("info",cardInfo);
        activity.startActivity(intent);
    }

    @Override
    public void showSuEmotionActivity(EmotionInfo emotionInfo) {
        Intent intent = new Intent(activity,SuEmotionActivity.class);
        intent.putExtra("info",emotionInfo);
        activity.startActivity(intent);
    }
}
