package com.downloader;

/**
 * Created by chenxunlin01 on 2016/12/12.
 */

public class DownloadException extends Exception {

    private int httpCode = -1;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
