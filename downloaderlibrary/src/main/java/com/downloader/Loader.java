package com.downloader;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;


import com.downloader.bean.DownloaderInfo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by chenxunlin01 on 2016/10/27.
 */
public abstract class Loader implements ILoader, Runnable {

    public static final String TAG = "Loader";

    protected String id;

    public enum Status {
        PENDING,
        RUNNING,
        PAUSE,
        ERROR,
        SUCCESS
    }

    private Status status = Status.PENDING;

    public static final int LOADER_STATUS_PROGRESS_UPDATE = 0x1;
    public static final int LOADER_STATUS_ERROR = 0x2;
    public static final int LOADER_STATUS_SUCCESS = 0x3;
    public static final int LOADER_STATUS_PAUSE = 0x4;

    private AtomicBoolean mStoped = new AtomicBoolean(false);

    protected DownloaderInfo info;
    private DownloaderLinstenerHandler handler;
    private MulLoader mulLoader;

    // 当前要跳过的字节
    protected long skipSize;

    public Loader(DownloaderInfo info, MulLoader mulLoader) {
        this.id = info.getId();
        this.info = info;
        handler = new DownloaderLinstenerHandler(this);
        this.mulLoader = mulLoader;
    }

    public boolean isStoped() {
        return mStoped.get();
    }

    public void setStop(boolean stop) {
        this.mStoped.set(stop);
    }

    public String getId() {
        return id;
    }

    @Override
    public void onPreExecute() {
        Log.e(TAG, "onPreExecute: ");
    }

    @Override
    public void onLoading() {
        Log.e(TAG, "onLoading: ");

        status = Status.RUNNING;
        info.setStatus(DownloaderInfo.STATUS_STARTING);

        BufferedInputStream inputStream = null;
        HttpURLConnection mConnection = null;
        BufferedOutputStream bos = null;
        try {
            //创建文件
            File file = getDownloadFile(info.getUrl());
            if (file.exists()) {
                skipSize = file.length();
            } else {
                file = createFile(info.getUrl());
            }

            bos = new BufferedOutputStream(new FileOutputStream(file, true), 8192);
            mConnection = createHttpURLConnection(info.getUrl(), skipSize);

            int code = mConnection.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK && code != HttpURLConnection.HTTP_PARTIAL) {
                throw new Exception("http response error " + code);
            }

            long fileSize = mConnection.getContentLength() + skipSize;
            Log.e(TAG, "onLoading: " + skipSize + "-" + fileSize);
            long currSize = skipSize;

            inputStream = new BufferedInputStream(mConnection.getInputStream());
            byte[] bytes = new byte[8 * 1024];
            int l = -1;
            while (!mStoped.get() && (l = inputStream.read(bytes)) != -1) {
                bos.write(bytes, 0, l);
                currSize += l;

                int progress = getProgress(currSize, fileSize);
                onProgressUpdate(progress);
            }

            if (!mStoped.get()) {
                onSuccess();
            } else {
                onPause();
            }

        } catch (Exception e) {
            e.printStackTrace();
            onError(e);

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (mConnection != null) {
                mConnection.disconnect();
            }
        }
    }

    @Override
    public void onProgressUpdate(int progress) {
        Log.e(TAG, "onProgressUpdate: " + progress);

        Message msg = Message.obtain();
        msg.what = LOADER_STATUS_PROGRESS_UPDATE;
        msg.arg1 = progress;
        handler.sendMessage(msg);
    }

    @Override
    public void onError(Exception ex) {
        Log.e(TAG, "onError: " + ex.toString());

        status = Status.ERROR;
        info.setStatus(DownloaderInfo.STATUS_PAUSE);

        Message msg = Message.obtain();
        msg.what = LOADER_STATUS_ERROR;
        msg.obj = ex;
        handler.sendMessage(msg);
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause: ");

        mulLoader.loaderPause(getId());

        status = Status.PAUSE;
        info.setStatus(DownloaderInfo.STATUS_PAUSE);

        Message msg = Message.obtain();
        msg.what = LOADER_STATUS_PAUSE;
        handler.sendMessage(msg);
    }

    @Override
    public void onSuccess() {
        Log.e(TAG, "onSuccess: ");

        status = Status.SUCCESS;
        info.setStatus(DownloaderInfo.STATUS_COMPLETE);

        Message msg = Message.obtain();
        msg.what = LOADER_STATUS_SUCCESS;
        handler.sendMessage(msg);
    }

    @Override
    public void onFinished() {
        Log.e(TAG, "onFinished: ");

        mulLoader.removeLoadingTask(getId());
        //加载准备队列中的任务
        mulLoader.loaderNext();
    }

    @Override
    public void loader(Executor executor) {
        onPreExecute();
        executor.execute(this);
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        onLoading();
        onFinished();
    }

    private HttpURLConnection createHttpURLConnection(String dUrl, long startByte) throws IOException {
        URL url = new URL(dUrl);
        HttpURLConnection mConnection = (HttpURLConnection) url.openConnection();
        //设置参数
        mConnection.setDoInput(true);// 设置是否从httpUrlConnection读入，默认情况下是true;
        mConnection.setUseCaches(false);//请求不能使用缓存
        mConnection.setRequestMethod("GET");// 设定请求的方法为"GET"，默认是GET
        mConnection.setConnectTimeout(30000);
        mConnection.setReadTimeout(300000);
        mConnection.setRequestProperty("RANGE", "bytes=" + startByte + "-"); //设置断点续传的开始位置
        return mConnection;
    }

    private File getDownloadFile(String url) {
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Constant.DOWN_DIR
                + File.separator;
        int suffixIndex = url.lastIndexOf('.');
        String path = dir + url.hashCode() + url.substring(suffixIndex);
        return new File(path);
    }

    private File createFile(String url) throws IOException {
        File file = getDownloadFile(url);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    private int getProgress(long currSize, long totalSize) {
        return (int) ((100 * currSize) / totalSize);
    }

    abstract protected void notifyProgress(int progress);

    abstract protected void notifyError(Exception ex);

    abstract protected void notifyPause();

    abstract protected void notifySuccess();

    public static class DownloaderLinstenerHandler extends Handler {

        private final WeakReference<Loader> mLoaderRe;

        public DownloaderLinstenerHandler(Loader loader) {
            super(Looper.getMainLooper());
            this.mLoaderRe = new WeakReference<Loader>(loader);
        }

        @Override
        public void handleMessage(Message msg) {
            Loader loader = mLoaderRe.get();
            if (loader == null) {
                return;
            }

            switch (msg.what) {
                case LOADER_STATUS_PROGRESS_UPDATE:
                    loader.notifyProgress(msg.arg1);
                    break;
                case LOADER_STATUS_ERROR:
                    loader.notifyError((Exception) msg.obj);
                    break;
                case LOADER_STATUS_SUCCESS:
                    loader.notifySuccess();
                    break;

                case LOADER_STATUS_PAUSE:
                    loader.notifyPause();
                    break;
            }
        }
    }
}
