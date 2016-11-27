package com.cartoon.pictures.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cartoon.pictures.R;
import com.cartoon.pictures.business.bean.ImageDetailInfo;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by chenxunlin01 on 2016/11/23.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context context;

    public ImagePagerAdapter(Context context) {
        this.context = context;
    }

    private List<ImageDetailInfo> datas;

    public void setData(List<ImageDetailInfo> data) {
        this.datas = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        photoView.setBackgroundColor(Color.BLACK);
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Glide.with(context).load(datas.get(position).getUrl()).placeholder(R.drawable.pull_loading_1).into(photoView);
        return photoView;
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
