package com.cartoon.pictures.business;

import android.util.Log;

import com.cartoon.pictures.business.state.CartoonPicturesState;
import com.squareup.otto.Bus;

import rx.Subscriber;

/**
 * Created by chenxunlin01 on 2016/12/5.
 */
public abstract class ProgressSubscriber<T> extends Subscriber<T> {
    
    public static final String TAG = "ProgressSubscriber";

    private final Bus bus;
    private final int callingId;
    private final int page;

    public ProgressSubscriber(Bus bus,int callingId,int page) {
        this.bus = bus;
        this.callingId = callingId;
        this.page = page;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " + Thread.currentThread().getId());
        bus.post(createPageLoadingProgressEvent(callingId, true, page));
    }

    @Override
    public void onCompleted() {
        Log.e(TAG, "onCompleted: " + Thread.currentThread().getId());
        bus.post(createPageLoadingProgressEvent(callingId,false,page));
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + Thread.currentThread().getId());
        if(page == 1){
            bus.post(new CartoonPicturesState.ShowErrorEvent(callingId));
        }else{
            bus.post(createPageLoadingProgressEvent(callingId,false,page));
        }
    }

    @Override
    public void onNext(T t) {
        Log.e(TAG, "onNext: " + Thread.currentThread().getId());
    }

    private Object createPageLoadingProgressEvent(int callingId, boolean show, int page) {
        if (page > 1) {
            return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show, true);
        } else {
            return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show);
        }
    }
}
