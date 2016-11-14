package com.catoon.corelibrary;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

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
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(HtmlConverterFactory.create())
                .build();
    }

    public <T> T create(final Class<T> service){
        return retrofit.create(service);
    }



    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(TAG, "log: " + message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(12, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .build();

        return client;
    }
}
