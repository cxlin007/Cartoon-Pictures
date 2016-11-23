package com.cartoon.pictures.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cartoon.pictures.R;
import com.cartoon.pictures.base.CommonViewHolder;
import com.cartoon.pictures.business.bean.ImageInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class MainAdapter extends ABasisAdapter<CartoonPicturesController.CartoonPicturesUiCallbacks, ImageInfo> {

    private int column;

    public MainAdapter(Context context, int column) {
        super(context, R.layout.main_item);
        this.column = column;
    }

    public void setData(List<ImageInfo> data) {
        if (data != null) {
            this.data = data;
        }

        notifyDataSetChanged();
    }

    @Override
    protected void onBindViewHolder(CommonViewHolder holder, int position) {
        final ImageInfo imageInfo = getItem(position);
        Glide.with(mContext).load(imageInfo.getUrl())
                .into((ImageView) holder.getView(R.id.image_item));
        holder.setOnClickListener(R.id.image_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onMainItemClick(imageInfo);
            }
        });
    }
}
