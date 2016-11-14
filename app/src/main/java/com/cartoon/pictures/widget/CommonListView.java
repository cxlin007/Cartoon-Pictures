package com.cartoon.pictures.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.cartoon.pictures.uilibrary.widget.MListView;
import com.cartoon.pictures.uilibrary.widget.MProgressView;

/**
 * Created by chenxunlin01 on 2016/6/14.
 */
public class CommonListView {

    private MProgressView mProgressView;
    private MListView listView;
    private Activity activity;

    public CommonListView(Activity activity) {
        this.activity = activity;
        mProgressView = new MProgressView(activity);
        listView = new MListView(activity);
        mProgressView.setContentView(listView);
    }

    public CommonListView setDivider(Drawable divider){
        ((ListView)listView.getContent()).setDivider(divider);
        return this;
    }

    public CommonListView setDividerHeight(int height){
        ((ListView)listView.getContent()).setDividerHeight(height);
        return this;
    }

    public CommonListView setMListViewLinstener(MListView.MListViewLinstener listViewLinstener){
        listView.setListViewLinstener(listViewLinstener);
        return this;
    }

    public CommonListView setListAdapter(BaseAdapter adapter){
        listView.setListAdapter(adapter);
        return this;
    }

    public CommonListView setMProgressViewLinstener(MProgressView.MProgressViewLinstener progressViewLinstener){
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
        listView.setSecondaryProgressShown(flag);
    }

    public void showErrorView(){
        mProgressView.showErrorView();
    }
}
