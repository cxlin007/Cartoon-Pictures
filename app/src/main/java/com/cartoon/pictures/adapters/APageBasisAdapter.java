package com.cartoon.pictures.adapters;

import android.content.Context;
import com.catoon.corelibrary.bean.PageResult;

/**
 * Created by chenxunlin01 on 2016/4/6.
 */
public abstract class APageBasisAdapter<R, T, E extends PageResult> extends ABasisAdapter<R, T> {

    protected E gifPageResult;

    public APageBasisAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    public APageBasisAdapter(Context context, int itemLayoutId) {
        super(context, itemLayoutId);
    }

    public E getGifPageResult() {
        return gifPageResult;
    }
}
