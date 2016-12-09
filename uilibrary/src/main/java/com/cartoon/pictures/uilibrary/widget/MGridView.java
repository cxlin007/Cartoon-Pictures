package com.cartoon.pictures.uilibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cartoon.pictures.uilibrary.R;


/**
 * Created by chenxunlin01 on 2016/5/26.
 */
public class MGridView extends LinearLayout implements AbsListView.OnScrollListener {

    private View emptyView;
    protected GridViewWithHeaderAndFooter content;
    private BaseAdapter mAdapter;
    private View mSecondaryProgressView;
    private LayoutInflater inflater;
    private MListViewLinstener mListViewLinstener;

    private TextView loadingMore;
    private View stateTv;
    private View refreshing;

    public MGridView(Context context) {
        super(context);
        init(context);
    }

    public MGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        inflater = LayoutInflater.from(context);
        View empty = findViewById(R.id.INTERNAL_EMPTY_ID);
        if (empty == null) {
            emptyView = createEmpty(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams
                    .WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            addView(emptyView, lp);
        } else {
            emptyView = empty;
        }
        emptyView.setVisibility(View.GONE);

        GridViewWithHeaderAndFooter listView = (GridViewWithHeaderAndFooter) findViewById(R.id
                .INTERNAL_LIST_CONTAINER_ID);
        if (listView == null) {
            content = new GridViewWithHeaderAndFooter(context);
            content.setId(R.id.INTERNAL_LIST_CONTAINER_ID);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
            addView(content, lp);
        } else {
            content = listView;
        }
        content.setOnScrollListener(this);
        content.setEmptyView(emptyView);

        mSecondaryProgressView = inflater.inflate(R.layout.mui__status_frame_loading_view, null);
        loadingMore = (TextView) mSecondaryProgressView.findViewById(R.id.loading_more);
        stateTv = mSecondaryProgressView.findViewById(R.id.state_tv);
        refreshing = mSecondaryProgressView.findViewById(R.id.refreshing_icon);
        content.addFooterView(mSecondaryProgressView);
        mSecondaryProgressView.setVisibility(View.GONE);
    }

    public void showLoadingMore(boolean hasMore) {
        loadingMore.setVisibility(View.VISIBLE);
        loadingMore.setText(hasMore ? R.string.loading_more : R.string.has_no_more);
        stateTv.setVisibility(View.INVISIBLE);
        refreshing.setVisibility(View.INVISIBLE);
    }

    public void showLoadingProgress() {
        loadingMore.setVisibility(View.INVISIBLE);
        stateTv.setVisibility(View.VISIBLE);
        refreshing.setVisibility(View.VISIBLE);
    }

    protected View createEmpty(Context context) {
        TextView view = new TextView(context);
        view.setText("no data");
        return view;
    }

    public GridViewWithHeaderAndFooter getContent() {
        return content;
    }


    public void setListAdapter(BaseAdapter adapter) {
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

    /**
     * 是否正在加载中
     *
     * @return
     */
    public boolean isSecondaryProgressShown() {
        return refreshing.getVisibility() == View.VISIBLE;
    }

    /**
     * 显示进度条
     *
     * @param visible 显示还是隐藏进度条
     * @param hasMore 是否还有更多
     */
    public void setSecondaryProgressShown(boolean visible, boolean hasMore) {
        mSecondaryProgressView.setVisibility(View.VISIBLE);
        if (visible) {
            showLoadingProgress();
        } else {
            showLoadingMore(hasMore);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            // 当不滚动时
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                // 判断滚动到底部  //是否正在加载
                if ((view.getLastVisiblePosition() == (view.getCount() - 1) && !isSecondaryProgressShown())) {
                    onScrollToBottom();
                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    private void onScrollToBottom() {
        if (mListViewLinstener != null) {
            mListViewLinstener.onScrollToBottom();
        }
    }

    public void setListViewLinstener(MListViewLinstener mListViewLinstener) {
        this.mListViewLinstener = mListViewLinstener;
    }

    public static interface MListViewLinstener {

        public void onScrollToBottom();
    }

    public void flush() {
        mAdapter.notifyDataSetChanged();
    }
}
