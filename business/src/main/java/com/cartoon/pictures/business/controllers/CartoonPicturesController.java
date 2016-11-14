package com.cartoon.pictures.business.controllers;

import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.api.ApiServiceImpl;
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
            fetchImageListIfNeed(getId(ui));
        }
    }

    private void fetchImageListIfNeed(int callingId) {
        CartoonPicturesState.ImagePageInfo imagePageInfo = cartoonPicturesState.getImagePageInfo();
        if (imagePageInfo == null || Utils.isEmpty(imagePageInfo.getData())) {
            apiService.fetchImageList(callingId,0);
        }
    }

    @Override
    protected void populateUi(CartoonPicturesUi ui) {
        super.populateUi(ui);
        if (ui instanceof CartoonPicturesMainUi) {
            populateCartoonPicturesMainUi((CartoonPicturesMainUi) ui);
        }
    }

    private void populateCartoonPicturesMainUi(CartoonPicturesMainUi ui) {

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
                ((CartoonPicturesMainUi) ui).setData(cartoonPicturesState.getImagePageInfo());
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
            public void fetchImageList() {
                CartoonPicturesState.ImagePageInfo imagePageInfo = cartoonPicturesState.getImagePageInfo();
                if (imagePageInfo == null || Utils.isEmpty(imagePageInfo.getData())) {
                    apiService.fetchImageList(getId(ui),0);
                }else{
                    apiService.fetchImageList(getId(ui), imagePageInfo.nextPage());
                }
            }

            @Override
            public void onErrorRetry() {
                apiService.fetchImageList(getId(ui), 0);
            }
        };
    }

    public interface CartoonPicturesUiCallbacks {

        public void fetchImageList();

        public void onErrorRetry();

    }

    public interface CartoonPicturesUi extends BaseUiController.Ui<CartoonPicturesUiCallbacks> {
        void showLoadingProgress(boolean visible);

        void showError(Exception e);

        void showSecondaryLoadingProgress(boolean visible);

        void showRefreshProgress(boolean successOrFail);
    }

    public interface CartoonPicturesMainUi extends CartoonPicturesUi {
        void setData(CartoonPicturesState.ImagePageInfo imagePageInfo);
    }
}
