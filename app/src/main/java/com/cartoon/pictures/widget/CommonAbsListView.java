package com.cartoon.pictures.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.cartoon.pictures.uilibrary.widget.MListView;
import com.cartoon.pictures.uilibrary.widget.MProgressView;

/**
 * Created by chenxunlin01 on 2016/12/5.
 */
public abstract class CommonAbsListView<T extends AbsListView> {

    protected MProgressView mProgressView;
    protected MListView listView;
    protected Activity activity;

    public CommonAbsListView(Activity activity) {
        this.activity = activity;
        mProgressView = new MProgressView(activity);
        listView = createListView();
        setContentStyle((T) listView.getContent());
        mProgressView.setContentView(listView);

    }

    protected abstract MListView createListView();

    protected void setContentStyle(T t) {
    }

    public CommonAbsListView setListAdapter(BaseAdapter adapter) {
        listView.setListAdapter(adapter);
        return this;
    }

    public CommonAbsListView setMListViewLinstener(MListView.MListViewLinstener mListViewLinstener){
        listView.setListViewLinstener(mListViewLinstener);
        return this;
    }

    public CommonAbsListView setMProgressViewLinstener(MProgressView.MProgressViewLinstener progressViewLinstener) {
        mProgressView.setProgressViewLinstener(progressViewLinstener);
        return this;
    }

    public ViewGroup getRootContent() {
        return mProgressView;
    }

    public void setContentShown(boolean flag) {
        mProgressView.setContentShown(flag);
    }

    public void setSecondaryProgressShown(boolean flag) {
        listView.setSecondaryProgressShown(flag);
    }

    public void showErrorView() {
        mProgressView.showErrorView();
    }
}
