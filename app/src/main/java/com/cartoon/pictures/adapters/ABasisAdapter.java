package com.cartoon.pictures.adapters;

import android.content.Context;

/**
 * Created by chenxunlin01 on 2016/4/6.
 */
public abstract class ABasisAdapter<R,T> extends MultiCommonAdapter<T> {

    protected R mCallBack;

    public ABasisAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    public ABasisAdapter(Context context, int itemLayoutId){
        super(context,itemLayoutId);
    }

    public void setCallBack(R callBack){
        mCallBack = callBack;
    }
}
