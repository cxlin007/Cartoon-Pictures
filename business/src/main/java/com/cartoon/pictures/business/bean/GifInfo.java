package com.cartoon.pictures.business.bean;

import java.io.Serializable;

/**
 * Created by chenxunlin01 on 2016/11/29.
 */
public class GifInfo implements Serializable {

    private String remoteUrl;
    private String des;

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "GifInfo{" +
                "remoteUrl='" + remoteUrl + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
