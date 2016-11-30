package com.cartoon.pictures.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cartoon.pictures.R;

import java.util.ArrayList;

/**
 * 带指示点的viewpager
 * Created by chenxunlin01 on 2016/11/28.
 */
public class PointViewPager extends RelativeLayout implements ViewPager.OnPageChangeListener, PointPagerAdapter
        .OnDataChangeLinstener {

    private PointPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private ViewGroup pageTurningPoint;
    private ArrayList<ImageView> mPointViews = new ArrayList<ImageView>();

    public PointViewPager(Context context) {
        super(context);
        init();
    }

    public PointViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.point_view_pager, this, true);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pageTurningPoint = (ViewGroup) findViewById(R.id.pageTurningPoint);
    }

    public void setPagerAdapter(PointPagerAdapter pagerAdapter) {
        this.pagerAdapter = pagerAdapter;
        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.setOnDataChangeLinstener(this);
        setPageIndicator();
    }

    private void setPageIndicator() {
        pageTurningPoint.removeAllViews();
        mPointViews.clear();
        int count = pagerAdapter.getCount();
        for (int i = 0; i < count; i++) {
            // 翻页指示的点
            ImageView pointView = new ImageView(getContext());
            int padding = getResources().getDimensionPixelOffset(R.dimen.banner_point_padding);
            pointView.setPadding(padding, 0, padding, 0);
            if (mPointViews.isEmpty())
                pointView.setImageResource(R.drawable.point_foucs);
            else
                pointView.setImageResource(R.drawable.point_default);
            mPointViews.add(pointView);
            pageTurningPoint.addView(pointView);
        }

        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mPointViews.size(); i++) {
            mPointViews.get(position).setImageResource(R.drawable.point_foucs);
            if (position != i) {
                mPointViews.get(i).setImageResource(R.drawable.point_default);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDateChange() {
        setPageIndicator();
    }
}
