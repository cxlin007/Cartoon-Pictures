package com.cartoon.pictures.business.bean;

import java.util.List;

/**
 * 卡片
 * Created by chenxunlin01 on 2016/11/29.
 */
public class CardInfo {

    private String title;
    private String remoteUrl;

    private List<GifInfo> gifInfos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GifInfo> getGifInfos() {
        return gifInfos;
    }

    public void setGifInfos(List<GifInfo> gifInfos) {
        this.gifInfos = gifInfos;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    @Override
    public String toString() {
        return "CardInfo{" +
                "title='" + title + '\'' +
                ", remoteUrl='" + remoteUrl + '\'' +
                ", gifInfos=" + gifInfos +
                '}';
    }
}
