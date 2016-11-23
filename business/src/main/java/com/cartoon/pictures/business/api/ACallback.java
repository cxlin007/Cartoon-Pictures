package com.cartoon.pictures.business.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxunlin01 on 2016/11/23.
 */
public abstract class ACallback<T> implements Callback<T> {

    @Override
    final public void onResponse(Call<T> call, Response<T> response) {
        try {
            doResponse(call, response);
        } catch (Exception e) {
            e.printStackTrace();
            onFailure(call, e);
        }
        doFinish();
    }

    @Override
    final public void onFailure(Call<T> call, Throwable t) {
        doFailure(call, t);
        doFinish();
    }

    protected abstract void doResponse(Call<T> call, Response<T> response) throws Exception;

    protected abstract void doFailure(Call<T> call, Throwable t);

    protected abstract void doFinish();
}
