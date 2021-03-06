package com.catoon.corelibrary;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class RetrofitManager {

    public static final String TAG = "RetrofitManager";

    private Retrofit retrofit;

    public RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .client(createOkHttpClient())
                .baseUrl("http://qq")
                .addConverterFactory(HtmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }


    private OkHttpClient createOkHttpClient() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                Log.e(TAG, "log: " + message);
//            }
//        });
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(12, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        return client;
    }
}
