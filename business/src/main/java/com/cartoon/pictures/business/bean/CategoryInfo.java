package com.cartoon.pictures.business.bean;

import java.io.Serializable;

/**
 * Created by chenxunlin01 on 2016/12/1.
 */
public class CategoryInfo implements Serializable {

    private String key;
    private String des;
    private String remoteUrl;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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
        return "CategoryInfo{" +
                "key='" + key + '\'' +
                ", des='" + des + '\'' +
                ", remoteUrl='" + remoteUrl + '\'' +
                '}';
    }
}
