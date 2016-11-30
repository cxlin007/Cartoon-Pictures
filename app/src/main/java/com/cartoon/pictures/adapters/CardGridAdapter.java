package com.cartoon.pictures.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.cartoon.pictures.R;
import com.cartoon.pictures.base.CommonViewHolder;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.catoon.corelibrary.common.Utils;

import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class CardGridAdapter extends ABasisAdapter<CartoonPicturesController.CartoonPicturesUiCallbacks, GifInfo> {

    private static final int MAX_SIZE = 3;

    public CardGridAdapter(Context context) {
        super(context, R.layout.gif_item);
    }

    public void setData(List<GifInfo> data) {
        if (data != null) {
            this.data = data;
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = super.getCount();
        return count > MAX_SIZE ? MAX_SIZE : count;
    }

    @Override
    protected void onBindViewHolder(CommonViewHolder holder, int position) {
        final GifInfo gifInfo = getItem(position);
        String url = gifInfo.getRemoteUrl();
        Glide.with(mContext).load(url).asBitmap()
                .into((ImageView) holder.getView(R.id.photoView));
        holder.setText(R.id.des, gifInfo.getDes());
        holder.getView(R.id.gif_tag).setVisibility(Utils.isGif(url) ? View.VISIBLE : View.GONE);
        holder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onGifItemClick(gifInfo);
            }
        });
    }
}
