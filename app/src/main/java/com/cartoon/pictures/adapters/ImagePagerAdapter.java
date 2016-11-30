package com.cartoon.pictures.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cartoon.pictures.R;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
import com.cartoon.pictures.uilibrary.widget.ProgressWheel;
import com.cartoon.pictures.widget.PointPagerAdapter;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by chenxunlin01 on 2016/11/23.
 */
public class ImagePagerAdapter extends PointPagerAdapter<ImageDetailInfo> {

    private Context context;

    public ImagePagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View root = LayoutInflater.from(context).inflate(R.layout.image_detail_item, container, false);
        PhotoView photoView = (PhotoView) root.findViewById(R.id.photoView);
        final ProgressWheel progressWheel = (ProgressWheel) root.findViewById(R.id.item_progress_wheel);
        container.addView(root);
        Glide.with(context).load(datas.get(position).getUrl()).listener(new RequestListener<String, GlideDrawable>() {

            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean
                    isFirstResource) {
                progressWheel.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                           boolean isFromMemoryCache, boolean isFirstResource) {
                progressWheel.setVisibility(View.GONE);
                return false;
            }
        }).into(photoView);
        return root;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
