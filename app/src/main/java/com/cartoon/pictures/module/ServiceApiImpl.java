package com.cartoon.pictures.module;

import android.util.Log;

import com.cartoon.pictures.module.bean.ImageInfo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/13.
 */

public class ServiceApiImpl {

    private static final String TAG = "ServiceApiImpl";

    private ServiceApi serviceApi;

    public ServiceApiImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(createOkHttpClient())
                .baseUrl("http://qq")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(HtmlConverterFactory.create())
                .build();
        serviceApi = retrofit.create(ServiceApi.class);
    }

    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(12, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .build();

        return client;
    }

    public void fetchImageList() {
        Call<String> call = serviceApi.fetchImageList();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int code = response.code();
                Log.e(TAG, "onResponse: "+code);
                Log.e(TAG, "onResponse: "+response.body() );
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


}
