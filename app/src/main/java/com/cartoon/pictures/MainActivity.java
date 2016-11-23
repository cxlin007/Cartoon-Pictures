package com.cartoon.pictures;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.cartoon.pictures.adapters.MainAdapter;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.ImageInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.uilibrary.widget.MListView;
import com.cartoon.pictures.uilibrary.widget.MProgressView;
import com.cartoon.pictures.widget.CommonGridView;

import java.util.List;

public class MainActivity extends BaseActivity implements CartoonPicturesController.CartoonPicturesMainUi,
        MProgressView.MProgressViewLinstener, MListView.MListViewLinstener {

    private CommonGridView commonGridView;
    private MainAdapter mainAdapter;
    private CartoonPicturesController.CartoonPicturesUiCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout rootLayout = (FrameLayout) findViewById(R.id.root_layout);

        mainAdapter = new MainAdapter(this, 3);
        commonGridView = new CommonGridView(this);
        commonGridView.setSpace((int) getResources().getDimension(R.dimen.grid_space))
                .setListAdapter(mainAdapter)
                .setMProgressViewLinstener(this)
                .setMListViewLinstener(this);
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
    public void setData(List<ImageInfo> data) {
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
    public void showSecondaryLoadingProgress(boolean visible) {
        commonGridView.setSecondaryProgressShown(visible);
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

    @Override
    public void onScrollToBottom() {
        mCallbacks.fetchImageList();
    }
}
