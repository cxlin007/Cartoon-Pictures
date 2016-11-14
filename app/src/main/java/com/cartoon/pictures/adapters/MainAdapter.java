package com.cartoon.pictures.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.cartoon.pictures.R;
import com.cartoon.pictures.base.CommonViewHolder;
import com.cartoon.pictures.business.bean.ImageInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.business.state.CartoonPicturesState;
import com.squareup.picasso.Picasso;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class MainAdapter extends ABasisAdapter<CartoonPicturesController.CartoonPicturesUiCallbacks, ImageInfo> {

    public MainAdapter(Context context) {
        super(context, R.layout.main_item);
    }

    public void setImagePageInfo(CartoonPicturesState.ImagePageInfo imagePageInfo) {
        this.pageInfo = imagePageInfo;
        notifyDataSetChanged();
    }

    @Override
    protected void onBindViewHolder(CommonViewHolder holder, int position) {
        final ImageInfo imageInfo = getItem(position);
        Picasso.with(mContext).load(imageInfo.getUrl()).placeholder(R.color.mui__transparent)
                .into((ImageView) holder.getView(R.id.image_item));

    }
}
