package com.downloader;



import com.downloader.bean.DownloaderInfo;

import java.util.ArrayList;

/**
 * Created by chenxunlin01 on 2016/10/27.
 */
public class Task extends Loader {

    private ArrayList<IDownloaderLinstener> linsteners = new ArrayList<>();

    public Task(DownloaderInfo downloaderInfo, MulLoader mulLoader,DownloaderManager downloaderManager) {
        super(downloaderInfo, mulLoader,downloaderManager);
    }

    public Task(DownloaderInfo downloaderInfo, IDownloaderLinstener linstener, MulLoader mulLoader,DownloaderManager downloaderManager) {
        super(downloaderInfo, mulLoader,downloaderManager);
        addLinstener(linstener);
    }

    public void addLinstener(IDownloaderLinstener linstener) {
        if (!linsteners.contains(linstener)) {
            linsteners.add(linstener);
        }
    }

    public boolean removeLinstener(IDownloaderLinstener linstener) {
        return linsteners.remove(linstener);
    }

    @Override
    protected void notifyProgress(int progress) {
        for (IDownloaderLinstener linstener : linsteners) {
            linstener.downloaderProgressChange(progress);
        }
    }

    @Override
    protected void notifyError(DownloadException ex) {
        for (IDownloaderLinstener linstener : linsteners) {
            linstener.downloaderError(ex);
        }
    }

    @Override
    protected void notifyPause() {
        for (IDownloaderLinstener linstener : linsteners) {
            linstener.downloaderPause();
        }
    }

    @Override
    protected void notifySuccess() {
        for (IDownloaderLinstener linstener : linsteners) {
            linstener.downloaderSuccess();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return info != null ? info.equals(task.info) : task.info == null;

    }

    @Override
    public int hashCode() {
        return info != null ? info.hashCode() : 0;
    }

}
