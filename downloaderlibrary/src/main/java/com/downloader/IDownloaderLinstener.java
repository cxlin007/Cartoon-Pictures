package com.downloader;

/**
 * Created by chenxunlin01 on 2016/10/26.
 */
public interface IDownloaderLinstener {


    public void downloaderPause();

    public void downloaderError(Exception ex);

    public void downloaderSuccess();

    public void downloaderProgressChange(int progress);

}
