package com.cartoon.pictures;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.cartoon.pictures.adapters.ImagePagerAdapter;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.uilibrary.widget.MProgressView;
import com.cartoon.pictures.widget.PointViewPager;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = getIntent().getStringExtra("url");

//        FrameLayout rootLayout = (FrameLayout) findViewById(R.id.root_layout);
        FrameLayout rootLayout = null;
        mProgressView = new MProgressView(this);
        PointViewPager pointViewPager = new PointViewPager(this);
        pointViewPager.setBackgroundColor(Color.BLACK);
        imagePagerAdapter = new ImagePagerAdapter(this);
        pointViewPager.setPagerAdapter(imagePagerAdapter);
        mProgressView.setContentView(pointViewPager);
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
