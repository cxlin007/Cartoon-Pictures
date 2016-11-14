package com.cartoon.pictures.uilibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cartoon.pictures.uilibrary.R;


/**
 * Created by chenxunlin01 on 2016/5/26.
 */
public class MListView extends FrameLayout implements AbsListView.OnScrollListener{

    private View emptyView;
    private AbsListView content;
    private BaseAdapter mAdapter;
    private View mSecondaryProgressView;
    private LayoutInflater inflater;
    private MListViewLinstener mListViewLinstener;

    public MListView(Context context) {
        super(context);
        init(context);
    }

    public MListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        inflater = LayoutInflater.from(context);
        View empty = findViewById(R.id.INTERNAL_EMPTY_ID);
        if(empty == null){
            emptyView = createEmpty(context);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            addView(emptyView,lp);
        }else{
            emptyView = empty;
        }

        AbsListView listView = (AbsListView) findViewById(R.id.INTERNAL_LIST_CONTAINER_ID);
        if(listView == null){
            content = createListView(context);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            addView(content,lp);
        }else{
            content = listView;
        }
        content.setOnScrollListener(this);
        content.setEmptyView(emptyView);

        mSecondaryProgressView = inflater.inflate(R.layout.mui__status_frame_loading_view,null);
        mSecondaryProgressView.setVisibility(View.GONE);
        LayoutParams mSLp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,Gravity.BOTTOM);
        addView(mSecondaryProgressView,mSLp);
    }

    protected View createEmpty(Context context){
        TextView view = new TextView(context);
        view.setText("no data");
        return view;
    }

    protected AbsListView createListView(Context context){
        return new ListView(context);
    }

    public AbsListView getContent() {
        return content;
    }

    public void setListAdapter(BaseAdapter adapter){
        mAdapter = adapter;
        content.setAdapter(mAdapter);
        ensureList();
    }

    private void ensureList() {
        if (mAdapter == null || mAdapter.isEmpty()) {
            content.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            content.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    public void setSecondaryProgressShown(boolean visible) {
        mSecondaryProgressView.setVisibility(visible?View.VISIBLE:View.GONE);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            // 当不滚动时
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                // 判断滚动到底部
                if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                    onScrollToBottom();
                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    private void onScrollToBottom(){
        if(mListViewLinstener!=null){
            mListViewLinstener.onScrollToBottom();
        }
    }

    public void setListViewLinstener(MListViewLinstener mListViewLinstener) {
        this.mListViewLinstener = mListViewLinstener;
    }

    public static interface MListViewLinstener{

        public void onScrollToBottom();
    }

    public void flush(){
        mAdapter.notifyDataSetChanged();
    }
}
