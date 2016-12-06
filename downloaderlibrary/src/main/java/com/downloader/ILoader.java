package com.downloader;


import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * Created by chenxunlin01 on 2016/10/27.
 */
public interface ILoader {

    //准备中
    public void onPreExecute();
    //下载中
    public void onLoading();
    //进度更新
    public void onProgressUpdate(int progress);
    //下咋暂停
    public void onPause();
    //下载异常
    public void onError(Exception ex);
    //下载成功
    public void onSuccess();
    //下载完成
    public void onFinished();
    //执行下载
    public void loader(Executor executor) throws IOException;

}
