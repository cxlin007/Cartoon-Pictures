package com.cartoon.pictures.activities;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.cartoon.pictures.R;
import com.cartoon.pictures.adapters.CardAdapter;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.uilibrary.widget.GridViewWithHeaderAndFooter;
import com.cartoon.pictures.uilibrary.widget.MProgressView;
import com.cartoon.pictures.widget.CommonGridView;
import com.catoon.corelibrary.common.Utils;

import java.util.List;

public class MainActivity extends BaseActivity implements CartoonPicturesController.CartoonPicturesMainUi,
        MProgressView.MProgressViewLinstener {

    private CommonGridView commonGridView;
    private CardAdapter mainAdapter;
    private CartoonPicturesController.CartoonPicturesUiCallbacks mCallbacks;
    private int line = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        mainAdapter = new CardAdapter(this);
        commonGridView = new CommonGridView(this) {

            @Override
            protected void setContentStyle(GridViewWithHeaderAndFooter gridView) {
                super.setContentStyle(gridView);
                gridView.setNumColumns(1);
                int spaceDP = Utils.dip2px(MainActivity.this, line);
                gridView.setVerticalSpacing(spaceDP);
                gridView.setVerticalScrollBarEnabled(false);
            }
        };
        commonGridView.setListAdapter(mainAdapter).setMProgressViewLinstener(this).setMoreEnable(false);
        rootLayout.addView(commonGridView.getRootContent());
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
    public void setData(List<CardInfo> data) {
        mainAdapter.setData(data);
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
    }

    @Override
    public void showRefreshProgress(boolean successOrFail) {

    }

    @Override
    public void setCallbacks(CartoonPicturesController.CartoonPicturesUiCallbacks callbacks) {
        mCallbacks = callbacks;
        mainAdapter.setCallBack(callbacks);
    }

    @Override
    public void onErrorRetry() {
        mCallbacks.onErrorRetry();
    }


}
