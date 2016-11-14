package com.catoon.corelibrary.controllers;

import com.catoon.corelibrary.Display;
import com.catoon.corelibrary.EvnManager;
import com.squareup.otto.Bus;

/**
 * Created by chenxunlin01 on 2016/4/6.
 */
public abstract class BaseController {

    private final Bus mBus;
    private Display display;

    public BaseController() {
        this.mBus = EvnManager.getBus();
    }

    public final void init(){
        onInited();
    }

    public final void suspend() {
        onSuspended();
    }

    protected void onInited() {}

    protected void onSuspended() {}

    public void registerBus() {
        mBus.register(this);
    }

    public void unRegisterBus() {
        mBus.unregister(this);
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
