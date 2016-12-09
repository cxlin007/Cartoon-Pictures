package com.cartoon.pictures.widget;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cartoon.pictures.uilibrary.widget.GridViewWithHeaderAndFooter;
import com.cartoon.pictures.uilibrary.widget.MGridView;
import com.cartoon.pictures.uilibrary.widget.MProgressView;

/**
 * Created by chenxunlin01 on 2016/12/5.
 */
public class CommonGridView {

    protected MProgressView mProgressView;
    protected MGridView listView;
    protected Activity activity;
    private boolean isMoreEnable = false;

    public CommonGridView(Activity activity) {
        this.activity = activity;
        mProgressView = new MProgressView(activity);
        listView = new MGridView(activity);
        setContentStyle(listView.getContent());
        mProgressView.setContentView(listView);

    }

    protected void setContentStyle(GridViewWithHeaderAndFooter gridView) {
    }

    public CommonGridView setListAdapter(BaseAdapter adapter) {
        listView.setListAdapter(adapter);
        return this;
    }

    public CommonGridView setMListViewLinstener(MGridView.MListViewLinstener mListViewLinstener) {
        listView.setListViewLinstener(mListViewLinstener);
        return this;
    }

    public CommonGridView setMProgressViewLinstener(MProgressView.MProgressViewLinstener progressViewLinstener) {
        mProgressView.setProgressViewLinstener(progressViewLinstener);
        return this;
    }

    public CommonGridView setMoreEnable(boolean moreEnable) {
        isMoreEnable = moreEnable;
        return this;
    }

    public ViewGroup getRootContent() {
        return mProgressView;
    }

    public void setContentShown(boolean flag) {
        mProgressView.setContentShown(flag);
    }

    public void setSecondaryProgressShown(boolean flag, boolean hasMore) {
        if (!isMoreEnable) {
            return;
        }
        listView.setSecondaryProgressShown(flag, hasMore);
    }

    public void showErrorView() {
        mProgressView.showErrorView();
    }
}
