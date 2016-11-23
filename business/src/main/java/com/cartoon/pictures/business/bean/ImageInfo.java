package com.cartoon.pictures.business.bean;

/**
 * Created by Administrator on 2016/11/13.
 */

public class ImageInfo {

    private String url;
    private String detailUrl;

    public ImageInfo() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "url='" + url + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }
}
