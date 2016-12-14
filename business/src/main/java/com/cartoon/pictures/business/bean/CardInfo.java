package com.cartoon.pictures.business.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 卡片
 * Created by chenxunlin01 on 2016/11/29.
 */
public class CardInfo implements Serializable {

    private String title;
    private String remoteUrl;
    private String key;

    private List<? extends GifInfo> gifInfos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<? extends GifInfo> getGifInfos() {
        return gifInfos;
    }

    public void setGifInfos(List<? extends GifInfo> gifInfos) {
        this.gifInfos = gifInfos;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "CardInfo{" +
                "title='" + title + '\'' +
                ", remoteUrl='" + remoteUrl + '\'' +
                ", key='" + key + '\'' +
                ", gifInfos=" + gifInfos +
                '}';
    }
}
