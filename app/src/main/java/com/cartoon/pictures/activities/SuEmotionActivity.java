package com.cartoon.pictures.activities;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.pictures.R;
import com.cartoon.pictures.adapters.EmotionGridAdapter;
import com.cartoon.pictures.adapters.SuEmotionGridAdapter;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.EmotionInfo;
import com.cartoon.pictures.business.bean.EmotionPageResult;
import com.cartoon.pictures.business.bean.GifPageResult;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.uilibrary.widget.GridViewWithHeaderAndFooter;
import com.cartoon.pictures.uilibrary.widget.MProgressView;
import com.cartoon.pictures.widget.CommonGridView;
import com.catoon.corelibrary.common.Utils;

public class SuEmotionActivity extends BaseActivity implements CartoonPicturesController.CartoonPicturesSuEmotionUi,MProgressView.MProgressViewLinstener {

    private int line = 8;

    private CartoonPicturesController.CartoonPicturesUiCallbacks mCallbacks;
    private SuEmotionGridAdapter suEmotionGridAdapter;
    private CommonGridView commonGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion);

        suEmotionGridAdapter = new SuEmotionGridAdapter(this);

        TextView title = (TextView) findViewById(R.id.tool_title);
        title.setText(getEmotionInfo().getDes());

        commonGridView = new CommonGridView(this) {
            @Override
            public void setContentStyle(GridViewWithHeaderAndFooter listView) {
                super.setContentStyle(listView);
                listView.setNumColumns(3);
                int spaceDP = Utils.dip2px(SuEmotionActivity.this, line);
                listView.setPadding(spaceDP, 0, spaceDP, 0);
                listView.setHorizontalSpacing(spaceDP);
                listView.setVerticalSpacing(spaceDP);
                listView.setVerticalScrollBarEnabled(false);
            }
        };
        commonGridView.setListAdapter(suEmotionGridAdapter)
        .setMProgressViewLinstener(this);
        ((ViewGroup) findViewById(R.id.root_layout)).addView(commonGridView.getRootContent());
    }

    @Override
    public void onResume() {
        super.onResume();
        getController().attachUi(this);
    }

    @Override
    public void onPause() {
        getController().detachUi(this);
        super.onPause();
    }

    @Override
    protected CartoonPicturesController getController() {
        return imContext.getCartoonPicturesController();
    }

    @Override
    public void setData(GifPageResult data) {
        suEmotionGridAdapter.setData(data);
    }

    @Override
    public GifPageResult getGifPageResult() {
        return suEmotionGridAdapter.getGifPageResult();
    }

    @Override
    public EmotionInfo getEmotionInfo() {
        return (EmotionInfo) getIntent().getSerializableExtra("info");
    }

    @Override
    public void setCallbacks(CartoonPicturesController.CartoonPicturesUiCallbacks callbacks) {
        mCallbacks = callbacks;
        suEmotionGridAdapter.setCallBack(mCallbacks);
    }

    @Override
    public void showLoadingProgress(boolean visible) {
        if (visible) {
            commonGridView.setContentShown(false);
        } else {
            commonGridView.setContentShown(true);
        }
    }

    @Override
    public void showError(Exception e) {
        commonGridView.showErrorView();
    }

    @Override
    public void showSecondaryLoadingProgress(boolean visible, boolean hasMore) {
        commonGridView.setSecondaryProgressShown(visible, hasMore);
    }

    @Override
    public void showRefreshProgress(boolean successOrFail) {

    }

    @Override
    public void onErrorRetry() {
        mCallbacks.onErrorRetry();
    }
}
