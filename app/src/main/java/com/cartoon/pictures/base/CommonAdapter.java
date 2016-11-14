package com.cartoon.pictures.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cartoon.pictures.business.bean.PageInfo;

/**
 * Created by chenxunlin01 on 2016/4/21.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected PageInfo<T> pageInfo;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, int itemLayoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return pageInfo != null ? pageInfo.getData().size() : 0;
    }

    @Override
    public T getItem(int position) {
        return pageInfo.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CommonViewHolder viewHolder = onCreateViewHolder(position,convertView,parent);
        onBindViewHolder(viewHolder,position);
        return viewHolder.getConvertView();
    }


    protected CommonViewHolder onCreateViewHolder(int position, View convertView,ViewGroup parent){
        return CommonViewHolder.get(mContext, convertView, parent, mItemLayoutId,position);
    };

    protected abstract void onBindViewHolder(CommonViewHolder holder, int position);
}
