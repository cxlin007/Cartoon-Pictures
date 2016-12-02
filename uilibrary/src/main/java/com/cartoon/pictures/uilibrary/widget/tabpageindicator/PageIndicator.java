package com.cartoon.pictures.uilibrary.widget.tabpageindicator;

import android.support.v4.view.ViewPager;

/**
 * 页面指示器
 * Created by zhushi_91 on 2015/9/24.
 */
public interface PageIndicator extends ViewPager.OnPageChangeListener {

    /**
     * 设置viewPager
     *
     * @param view
     */
    void setViewPager(ViewPager view);

    /**
     * 设置viewPager
     *
     * @param view
     * @param initialPosition
     */
    void setViewPager(ViewPager view, int initialPosition);

    /**
     * 设置当前项
     *
     * @param item
     */
    void setCurrentItem(int item);

    /**
     * 设置页面监听
     *
     * @param listener
     */
    void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);

    /**
     * 通知数据改变
     */
    void notifyDataSetChanged();
}

