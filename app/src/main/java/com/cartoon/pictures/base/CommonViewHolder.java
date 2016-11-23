package com.cartoon.pictures.base;


import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cartoon.pictures.R;

/**
 * Created by chenxunlin01 on 2016/5/31.
 */
public class CommonViewHolder<T> {

    private final SparseArray<View> mViews;
    private View mConvertView;

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position){
        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(R.id.common_view_holder,this);
    }

    public static CommonViewHolder get(Context context,View convertView,ViewGroup parent,int layoutId,int position){
        if(convertView == null){
            return new CommonViewHolder(context,parent,layoutId,position);
        }

        return (CommonViewHolder) convertView.getTag(R.id.common_view_holder);
    }

    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view==null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public CommonViewHolder setText(int viewId,String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonViewHolder setText(int viewId,int resId){
        TextView tv = getView(viewId);
        tv.setText(resId);
        return this;
    }

    public CommonViewHolder setImageResource(int viewId,int resId){
        ImageView tv = getView(viewId);
        tv.setImageResource(resId);
        return this;
    }

    public CommonViewHolder setOnClickListener(int viewId,View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public View getConvertView(){
        return mConvertView;
    }

}
