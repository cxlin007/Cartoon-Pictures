package com.downloader;

import android.util.Log;


import com.downloader.bean.DownloaderInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程下载器
 * Created by chenxunlin01 on 2016/10/26.
 */
public class MulLoader {

    public static final String TAG = "MullLoader";

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;
    private static final BlockingQueue<Runnable> POOL_QUEUE_TASK = new LinkedBlockingQueue<>(56);
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "MulLoader #" + mCount.getAndIncrement());
        }
    };

    public final Executor mExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
            TimeUnit.SECONDS, POOL_QUEUE_TASK, sThreadFactory);

    //准备中的队列
    private List<Task> preparePool = Collections.synchronizedList(new ArrayList<Task>());
    //下载中的队列
    private ConcurrentHashMap loadingPool = new ConcurrentHashMap();
    //暂停中的队列
    private ConcurrentHashMap pausePool = new ConcurrentHashMap();

    private int maxLoadingSize = 3;


    public MulLoader() {
    }

    public void start(DownloaderInfo info, final IDownloaderLinstener downloaderLinstener) throws Exception {
        if (info == null) {
            Log.d(TAG, "start: DownloaderInfo is null");
            throw new Exception("DownloaderInfo is null");
        }

        if (downloaderLinstener == null) {
            Log.d(TAG, "start: DownloaderLinstener is null");
            throw new Exception("DownloaderLinstener is null");
        }

        Task task = new Task(info, downloaderLinstener, this);

        //判断有没在队列中
        if (preparePool.contains(task) || loadingPool.contains(task)) {
            downloaderLinstener.downloaderError(new Exception("haved a task in pool"));
            return;
        }

        //判断正在下载的任务是否大于设置的最大个数
        if (loadingPool.size() >= maxLoadingSize) {
            //放到准备队列中
            preparePool.add(task);

        } else {
            loadingPool.put(task.getId(), task);
            mExecutor.execute(task);
        }
    }

    public void stop(DownloaderInfo info) throws Exception {
        if (info == null) {
            Log.d(TAG, "stop: DownloaderInfo is null");
            throw new Exception("DownloaderInfo is null");
        }

        Task mTask = (Task) loadingPool.get(new Task(info, this).getId());
        if (mTask != null) {
            mTask.setStop(true);
        }
    }

    public void reStart(DownloaderInfo info) throws Exception {
        if (info == null) {
            Log.d(TAG, "reStart: DownloaderInfo is null");
            throw new Exception("DownloaderInfo is null");
        }

        Task mTask = (Task) pausePool.get(new Task(info, this).getId());
        if (mTask != null) {
            mTask.setStop(false);
            loadingPool.put(mTask.getId(), mTask);
            mExecutor.execute(mTask);
        }
    }

    public void cancel() {

    }

    public void loaderPause(String id) {
        Task task = (Task) loadingPool.remove(id);
        pausePool.put(task.getId(), task);
    }

    public void loaderNext() {
        try {
            if (!preparePool.isEmpty()) {
                Task task = preparePool.remove(0);
                loadingPool.put(task.getId(), task);
                mExecutor.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeLoadingTask(String id) {
        loadingPool.remove(id);
    }
}
