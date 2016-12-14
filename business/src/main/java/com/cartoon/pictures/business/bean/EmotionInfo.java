package com.cartoon.pictures.business.bean;

/**
 * Created by chenxunlin01 on 2016/12/13.
 */

public class EmotionInfo extends GifInfo {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "EmotionInfo{" +
                "key='" + key + '\'' +
                super.toString() +
                '}';
    }
}
