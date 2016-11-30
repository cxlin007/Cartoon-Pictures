package com.cartoon.pictures.business.controllers;

import com.cartoon.pictures.business.BDisplay;
import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.api.ApiServiceImpl;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
import com.cartoon.pictures.business.bean.ImageInfo;
import com.cartoon.pictures.business.state.CartoonPicturesState;
import com.catoon.corelibrary.common.Utils;
import com.catoon.corelibrary.controllers.BaseUiController;
import com.squareup.otto.Subscribe;

import java.util.List;
import java.util.Set;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class CartoonPicturesController extends BaseUiController<CartoonPicturesController.CartoonPicturesUi,
        CartoonPicturesController.CartoonPicturesUiCallbacks> {

    private ApiServiceImpl apiService;
    private CartoonPicturesState cartoonPicturesState;

    public CartoonPicturesController() {
        apiService = new ApiServiceImpl();
        cartoonPicturesState = BusinessManager.getCartoonPicturesState();
    }

    @Override
    protected void onInited() {
        super.onInited();
        registerBus();
    }

    @Override
    protected void onSuspended() {
        super.onSuspended();
        unRegisterBus();
    }

    @Override
    protected void onUiAttached(CartoonPicturesUi ui) {
        super.onUiAttached(ui);
        if (ui instanceof CartoonPicturesMainUi) {
            fetchExpressionMainIfNeed(getId(ui));
        } else if (ui instanceof CartoonPicturesDetailUi) {
            fetchImageDetailIfNeed(getId(ui), ((CartoonPicturesDetailUi) ui).getUrl());
        }
    }

    private void fetchExpressionMainIfNeed(int callingId) {
        if (Utils.isEmpty(cartoonPicturesState.getCardInfos())) {
            apiService.fetchExpressionMain(callingId);
        }
    }

    private void fetchImageDetailIfNeed(int callingId, String url) {
        List<ImageDetailInfo> imageDetailInfos = cartoonPicturesState.getImageDetailInfos(url);
        if (imageDetailInfos == null || Utils.isEmpty(imageDetailInfos)) {
            apiService.fetchImageDetail(callingId, url);
        }
    }

    @Override
    protected void populateUi(CartoonPicturesUi ui) {
        super.populateUi(ui);
        if (ui instanceof CartoonPicturesMainUi) {
            populateCartoonPicturesMainUi((CartoonPicturesMainUi) ui);
        } else if (ui instanceof CartoonPicturesDetailUi) {
            populateCartoonPicturesDetailUi((CartoonPicturesDetailUi) ui);
        }
    }

    private void populateCartoonPicturesMainUi(CartoonPicturesMainUi ui) {
        ui.setData(cartoonPicturesState.getCardInfos());
    }

    private void populateCartoonPicturesDetailUi(CartoonPicturesDetailUi ui) {
        List<ImageDetailInfo> imageDetailInfos = cartoonPicturesState.getImageDetailInfos(ui.getUrl());
        ui.setData(imageDetailInfos);
    }

    @Subscribe
    public void onLoadingProgressVisibilityChanged(CartoonPicturesState.ShowLoadingProgressEvent event) {
        CartoonPicturesUi ui = (CartoonPicturesUi) findUi(event.mCallingId);
        if (ui == null) {
            return;
        }

        if (event.secondary) {
            ui.showSecondaryLoadingProgress(event.show);
        } else {
            ui.showLoadingProgress(event.show);
        }
    }

    @Subscribe
    public void onCartoonPicturesMainDataChange(CartoonPicturesState.CartoonPicturesMainDataChange event) {
        Set<CartoonPicturesUi> cartoonPicturesUis = getUis();
        for (CartoonPicturesUi ui : cartoonPicturesUis) {
            if (ui instanceof CartoonPicturesMainUi) {
                ((CartoonPicturesMainUi) ui).setData(cartoonPicturesState.getCardInfos());
            }
        }
    }

    @Subscribe
    public void onCartoonPicturesMainDetailChange(CartoonPicturesState.CartoonPicturesMainDetailChange event) {
        Set<CartoonPicturesUi> cartoonPicturesUis = getUis();
        for (CartoonPicturesUi ui : cartoonPicturesUis) {
            if (ui instanceof CartoonPicturesDetailUi) {
                List<ImageDetailInfo> imageDetailInfos = cartoonPicturesState.getImageDetailInfos(event.url);
                ((CartoonPicturesDetailUi) ui).setData(imageDetailInfos);
            }
        }
    }

    @Subscribe
    public void onShowErrorEvent(CartoonPicturesState.ShowErrorEvent event) {
        CartoonPicturesUi ui = (CartoonPicturesUi) findUi(event.mCallingId);
        if (ui == null) {
            return;
        }

        ui.showError(null);
    }


    @Override
    protected CartoonPicturesUiCallbacks createUiCallbacks(final CartoonPicturesUi ui) {
        return new CartoonPicturesUiCallbacks() {
            @Override
            public void fetchExpressionMain() {
                apiService.fetchExpressionMain(getId(ui));
            }

            @Override
            public void fetchImageDetail(String url) {
                apiService.fetchImageDetail(getId(ui), url);
            }

            @Override
            public void onErrorRetry() {
                if (ui instanceof CartoonPicturesMainUi) {
                    apiService.fetchExpressionMain(getId(ui));
                } else if (ui instanceof CartoonPicturesDetailUi) {
                    apiService.fetchImageDetail(getId(ui), ((CartoonPicturesDetailUi) ui).getUrl());
                }
            }

            @Override
            public void onGifItemClick(GifInfo gifInfo) {
                //展示弹出框
                if (ui instanceof CartoonPicturesMainUi) {
                    ((BDisplay) getDisplay()).showGifDialogActivity(gifInfo);
                }
            }
        };
    }

    public interface CartoonPicturesUiCallbacks {

        public void fetchExpressionMain();

        public void fetchImageDetail(String url);

        public void onErrorRetry();

        public void onGifItemClick(GifInfo gifInfo);

    }

    public interface CartoonPicturesUi extends BaseUiController.Ui<CartoonPicturesUiCallbacks> {
        void showLoadingProgress(boolean visible);

        void showError(Exception e);

        void showSecondaryLoadingProgress(boolean visible);

        void showRefreshProgress(boolean successOrFail);


    }

    public interface CartoonPicturesMainUi extends CartoonPicturesUi {
        void setData(List<CardInfo> data);
    }

    public interface CartoonPicturesDetailUi extends CartoonPicturesUi {

        void setData(List<ImageDetailInfo> data);

        String getUrl();
    }
}
