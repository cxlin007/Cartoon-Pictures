package com.cartoon.pictures.widget;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.cartoon.pictures.uilibrary.widget.MGridView;
import com.cartoon.pictures.uilibrary.widget.MListView;
import com.cartoon.pictures.uilibrary.widget.MProgressView;

/**
 * Created by chenxunlin01 on 2016/6/14.
 */
public class CommonGridView {

    private MProgressView mProgressView;
    private MGridView gridView;
    private Activity activity;

    public CommonGridView(Activity activity) {
        this.activity = activity;
        mProgressView = new MProgressView(activity);
        gridView = new MGridView(activity);
        gridView.setColumn(3);
        mProgressView.setContentView(gridView);
    }

    public CommonGridView setSpace(int space){
        ((GridView)gridView.getContent()).setHorizontalSpacing(space);
        ((GridView)gridView.getContent()).setVerticalSpacing(space);

        return this;
    }

    public CommonGridView setMListViewLinstener(MListView.MListViewLinstener listViewLinstener){
        gridView.setListViewLinstener(listViewLinstener);
        return this;
    }

    public CommonGridView setListAdapter(BaseAdapter adapter){
        gridView.setListAdapter(adapter);
        return this;
    }

    public CommonGridView setMProgressViewLinstener(MProgressView.MProgressViewLinstener progressViewLinstener){
        mProgressView.setProgressViewLinstener(progressViewLinstener);
        return this;
    }

    public ViewGroup getRootContent(){
        return mProgressView;
    }

    public void setContentShown(boolean flag){
        mProgressView.setContentShown(flag);
    }

    public void setSecondaryProgressShown(boolean flag){
        gridView.setSecondaryProgressShown(flag);
    }

    public void showErrorView(){
        mProgressView.showErrorView();
    }
}
