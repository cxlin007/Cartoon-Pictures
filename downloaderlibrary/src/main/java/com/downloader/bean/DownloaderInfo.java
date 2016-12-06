package com.downloader.bean;

/**
 * Created by chenxunlin01 on 2016/10/26.
 */
public class DownloaderInfo {

    public static final int STATUS_WAIT = 1;
    public static final int STATUS_STARTING = 2;
    public static final int STATUS_PAUSE = 3;
    public static final int STATUS_COMPLETE = 4;

    private String id;
    private int status = STATUS_WAIT;
    private String url;
    private int progress;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DownloaderInfo that = (DownloaderInfo) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
