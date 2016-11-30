package com.cartoon.pictures.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.cartoon.pictures.AndroidDisplay;
import com.cartoon.pictures.MainApplication;
import com.catoon.corelibrary.controllers.BaseUiController;

import java.lang.ref.WeakReference;

/**
 * Created by chenxunlin01 on 2016/6/13.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected AndroidDisplay mDisplay;
    protected MainApplication imContext;
    // 处理器
    protected MMHandler mmHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imContext = (MainApplication) getApplication();
        mDisplay = new AndroidDisplay(this);
        mmHandler = new MMHandler(this);
        if (getController() != null) {
            getController().setDisplay(mDisplay);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getController() != null) {
            getController().setDisplay(mDisplay);
            getController().init();
        }
    }

    @Override
    protected void onPause() {
        if (getController() != null) {
            getController().setDisplay(null);
            getController().suspend();
        }
        super.onPause();
    }

    protected abstract BaseUiController getController();


    /**
     * 发送延时空消息
     *
     * @param what
     * @param delay
     */
    protected void sendEmptyMessage(int what, long delay) {
        removeEmptyMessage(what);
        mmHandler.sendEmptyMessageDelayed(what, delay);
    }

    /**
     * 移除某个消息
     *
     * @param what
     */
    protected void removeEmptyMessage(int what) {
        mmHandler.removeMessages(what);
    }

    /**
     * 发送延时消息
     *
     * @param message
     * @param delay
     */
    protected void sendDelayMessage(Message message, long delay) {
        mmHandler.sendMessageDelayed(message, delay);
    }

    /**
     * 子类处理对应的消息
     *
     * @param msg
     */
    public void subHandleMessage(Message msg) {

    }

    /**
     * 主界面消息处理器，希望它存在的时候处理所有可能的消息
     */
    private static class MMHandler extends Handler {

        private final WeakReference<BaseActivity> mActivityRef;

        public MMHandler(BaseActivity context) {
            super(Looper.getMainLooper());

            mActivityRef = new WeakReference<BaseActivity>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity aActivity = mActivityRef.get();
            if (aActivity == null) {
                return;
            }

            // 如果已经调用了finish了。则不处理
            if (aActivity.isFinishing()) {
                return;
            }
            switch (msg.what) {
                default:
                    aActivity.subHandleMessage(msg);
                    break;
            }
        }
    }

}
