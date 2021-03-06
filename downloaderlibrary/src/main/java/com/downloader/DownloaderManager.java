package com.downloader;


import com.downloader.bean.DownloaderInfo;

/**
 * Created by chenxunlin01 on 2016/10/26.
 */
public class DownloaderManager {


    private MulLoader mulLoader;
    private String downloaderDir = Constant.DOWN_DIR;
    private static DownloaderManager downloaderManager;

    public static DownloaderManager instance() {
        if (downloaderManager == null) {
            downloaderManager = new DownloaderManager();
        }

        return downloaderManager;
    }

    private DownloaderManager() {
        mulLoader = new MulLoader(this);
    }

    public void setDownloaderDir(String dir){
        downloaderDir = dir;
    }

    public String getDownloaderDir() {
        return downloaderDir;
    }

    public void start(DownloaderInfo info, IDownloaderLinstener downloaderLinstener) {
        try {
            mulLoader.start(info, downloaderLinstener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(DownloaderInfo info){
        try {
            mulLoader.stop(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reStart(DownloaderInfo info){
        try {
            mulLoader.reStart(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancel(){

    }

}
