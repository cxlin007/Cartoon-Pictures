package com.catoon.corelibrary;

import com.squareup.otto.Bus;
/**
 * Created by chenxunlin01 on 2016/3/16.
 */
public class EvnManager {

    private static Bus bus;
    private static RetrofitManager retrofitManager;

    synchronized public static Bus getBus() {
        if (bus == null) {
            bus = new Bus();
        }

        return bus;
    }

    synchronized public static RetrofitManager getRetrofitManager() {
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager();
        }

        return retrofitManager;
    }
}
