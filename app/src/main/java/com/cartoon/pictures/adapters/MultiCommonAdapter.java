package com.cartoon.pictures.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.cartoon.pictures.base.CommonAdapter;
import com.cartoon.pictures.base.CommonViewHolder;

/**
 * Created by chenxunlin01 on 2016/6/1.
 */
public abstract class MultiCommonAdapter<T> extends CommonAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiCommonAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1);
        this.mMultiItemTypeSupport = multiItemTypeSupport;
    }

    public MultiCommonAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getItemViewType(position,getItem(position));

        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getViewTypeCount();

        return super.getViewTypeCount();
    }

    @Override
    protected CommonViewHolder onCreateViewHolder(int position, View convertView, ViewGroup parent) {
        if (mMultiItemTypeSupport != null)
            return CommonViewHolder.get(mContext, convertView, parent, mMultiItemTypeSupport.getLayoutId(getItemViewType(position)),position);

        return super.onCreateViewHolder(position, convertView,parent);
    }
}
