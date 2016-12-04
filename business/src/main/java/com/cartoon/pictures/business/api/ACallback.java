package com.cartoon.pictures.business.api;

import com.cartoon.pictures.business.state.CartoonPicturesState;
import com.squareup.otto.Bus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxunlin01 on 2016/11/23.
 */
public abstract class ACallback<T> implements Callback<T> {

    private final int page;
    private final int callingId;
    private Bus bus;

    public ACallback(Bus bus,int page, int callingId) {
        this.page = page;
        this.callingId = callingId;
        this.bus = bus;
    }

    @Override
    final public void onResponse(Call<T> call, Response<T> response) {
        Throwable ex = null;
        try {
            doResponse(call, response);
        } catch (Exception e) {
            e.printStackTrace();
            ex = e;
        }
        doFinish(ex);
    }

    @Override
    final public void onFailure(Call<T> call, Throwable t) {
        doFinish(t);
    }

    protected abstract void doResponse(Call<T> call, Response<T> response) throws Exception;

    protected void doFinish(Throwable ex) {
        if(ex == null){
            bus.post(createPageLoadingProgressEvent(callingId,false,page));
        }else{
            if(page == 1){
                bus.post(new CartoonPicturesState.ShowErrorEvent(callingId));
            }else{
                bus.post(createPageLoadingProgressEvent(callingId,false,page));
            }
        }
    }

    private Object createPageLoadingProgressEvent(int callingId, boolean show, int page) {
        if (page > 1) {
            return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show, true);
        } else {
            return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show);
        }
    }
}
