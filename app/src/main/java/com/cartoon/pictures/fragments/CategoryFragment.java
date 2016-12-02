package com.cartoon.pictures.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cartoon.pictures.adapters.GifAdapter;
import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.business.bean.GifPageResult;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.uilibrary.widget.MListView;
import com.cartoon.pictures.uilibrary.widget.MProgressView;
import com.cartoon.pictures.widget.CommonGridView;
import com.catoon.corelibrary.common.Utils;

/**
 * Created by chenxunlin01 on 2016/12/1.
 */
public class CategoryFragment extends BaseFragment implements MListView.MListViewLinstener, MProgressView
        .MProgressViewLinstener, CartoonPicturesController.CartoonPicturesSuCategoryUi {

    private int line = 8;
    private GifAdapter gifAdapter;
    private CartoonPicturesController.CartoonPicturesUiCallbacks mCallbacks;
    private CommonGridView commonGridView;

    public static CategoryFragment newInstance(CategoryInfo categoryInfo) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putSerializable("info", categoryInfo);
        return categoryFragment;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        commonGridView = new CommonGridView(getActivity());
        commonGridView.setSpace(Utils.dip2px(getActivity(), line));
        gifAdapter = new GifAdapter(getActivity());
        commonGridView.setListAdapter(gifAdapter).setMListViewLinstener(this).setMProgressViewLinstener(this);
        return commonGridView.getRootContent();
    }

    private CategoryInfo getCategoryInfo() {
        return (CategoryInfo) getArguments().getSerializable("info");
    }

    @Override
    protected CartoonPicturesController getController() {
        return imContext.getCartoonPicturesController();
    }

    @Override
    public void onScrollToBottom() {
        mCallbacks.fetchSuCategoryGifList(getSuCategoryInfo(), gifAdapter.getGifPageResult());
    }

    @Override
    public void onErrorRetry() {
        mCallbacks.onErrorRetry();
    }


    @Override
    public void setCallbacks(CartoonPicturesController.CartoonPicturesUiCallbacks callbacks) {
        mCallbacks = callbacks;
        gifAdapter.setCallBack(callbacks);
    }

    @Override
    public void setData(GifPageResult data) {
        gifAdapter.setData(data);
    }

    @Override
    public GifPageResult getGifPageResult() {
        return gifAdapter.getGifPageResult();
    }

    @Override
    public CategoryInfo getSuCategoryInfo() {
        return getCategoryInfo();
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
}
