package com.cartoon.pictures.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.fragments.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxunlin01 on 2016/12/1.
 */
public class CategoryAdapter extends FragmentStatePagerAdapter {

    private List<CategoryInfo> categoryInfos = new ArrayList<>();

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<CategoryInfo> categoryInfos) {
        this.categoryInfos = categoryInfos;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryFragment.newInstance(categoryInfos.get(position));
    }

    @Override
    public int getCount() {
        return categoryInfos == null ? 0 : categoryInfos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryInfos.get(position).getDes();
    }
}
