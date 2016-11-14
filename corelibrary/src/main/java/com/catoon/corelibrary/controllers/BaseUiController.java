package com.catoon.corelibrary.controllers;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by chenxunlin01 on 2016/3/17.
 */
abstract public class BaseUiController<U extends BaseUiController.Ui<UC>,UC> extends BaseController{

    public interface Ui<UC> {
        void setCallbacks(UC callbacks);
    }

    private final Set<U> mUis;

    public BaseUiController() {
        this.mUis = new CopyOnWriteArraySet<U>();
    }

    public synchronized final void attachUi(U ui){
        mUis.add(ui);
        ui.setCallbacks(createUiCallbacks(ui));
        onUiAttached(ui);
        populateUi(ui);
    }

    public synchronized final void detachUi(U ui){
        onUiDetached(ui);
        ui.setCallbacks(null);
        mUis.remove(ui);
    }

    protected synchronized U findUi(final int id){
        for (U ui: mUis) {
            if(getId(ui) == id){
                return ui;
            }
        }

        return null;
    }

    protected final Set<U> getUis(){
        return mUis;
    }

    protected int getId(U ui) {
        return ui.hashCode();
    }

    protected void onUiAttached(U ui) {
    }

    protected void onUiDetached(U ui) {
    }

    protected void populateUi(U ui) {
    }

    protected abstract UC createUiCallbacks(U ui);

}
