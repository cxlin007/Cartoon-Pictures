package com.cartoon.pictures;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.cartoon.pictures.adapters.ImagePagerAdapter;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.uilibrary.widget.MProgressView;

import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/23.
 */
public class ImageDetailActivity extends BaseActivity implements CartoonPicturesController.CartoonPicturesDetailUi {

    private CartoonPicturesController.CartoonPicturesUiCallbacks mCallbacks;
    private ImagePagerAdapter imagePagerAdapter;
    private String url;
    private MProgressView mProgressView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        url = getIntent().getStringExtra("url");

        FrameLayout rootLayout = (FrameLayout) findViewById(R.id.root_layout);
        mProgressView = new MProgressView(this);
        ViewPager mViewPager = new ViewPager(this);
        imagePagerAdapter = new ImagePagerAdapter(this);
        mViewPager.setAdapter(imagePagerAdapter);
        mProgressView.setContentView(mViewPager);
        rootLayout.addView(mProgressView.getRootView());
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
    public void setData(List<ImageDetailInfo> data) {
        imagePagerAdapter.setData(data);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void showLoadingProgress(boolean visible) {
        if (visible) {
            mProgressView.setContentShown(false);
        } else {
            mProgressView.setContentShown(true);
        }
    }

    @Override
    public void showError(Exception e) {
        mProgressView.showErrorView();
    }

    @Override
    public void showSecondaryLoadingProgress(boolean visible) {

    }

    @Override
    public void showRefreshProgress(boolean successOrFail) {

    }

    @Override
    public void setCallbacks(CartoonPicturesController.CartoonPicturesUiCallbacks callbacks) {
        mCallbacks = callbacks;
    }
}
