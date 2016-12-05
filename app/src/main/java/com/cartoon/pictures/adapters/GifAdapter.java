package com.cartoon.pictures.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cartoon.pictures.R;
import com.cartoon.pictures.base.CommonViewHolder;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.bean.GifPageResult;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.catoon.corelibrary.common.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class GifAdapter extends ABasisAdapter<CartoonPicturesController.CartoonPicturesUiCallbacks, GifInfo> {

    private GifPageResult gifPageResult;

    public GifAdapter(Context context) {
        super(context, R.layout.sucategory_gif_item);
    }

    public void setData(GifPageResult gifPageResult) {
        this.gifPageResult = gifPageResult;
        if(data == null){
            data = new ArrayList<>();
        }
        data.addAll(gifPageResult.items);
        notifyDataSetChanged();
    }

    public GifPageResult getGifPageResult() {
        return gifPageResult;
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
