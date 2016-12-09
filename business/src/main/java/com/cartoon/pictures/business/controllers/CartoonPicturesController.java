package com.cartoon.pictures.business.controllers;

import com.cartoon.pictures.business.BDisplay;
import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.api.ApiServiceImpl;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.bean.GifPageResult;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
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
        } else if (ui instanceof CartoonPicturesSuCategoryUi) {
            fetchCartoonPicturesSuCategoryIfNeed((CartoonPicturesSuCategoryUi) ui);
        }
    }

    private void fetchExpressionMainIfNeed(int callingId) {
        if (Utils.isEmpty(cartoonPicturesState.getCardInfos())) {
            apiService.fetchExpressionMain(callingId);
        }
    }

    private void fetchCartoonPicturesSuCategoryIfNeed(CartoonPicturesSuCategoryUi ui) {
        if (ui.getGifPageResult() == null) {
            apiService.fetchSuCategoryList(getId(ui), ui.getSuCategoryInfo(), ui.getGifPageResult());
        }
    }

    @Override
    protected void populateUi(CartoonPicturesUi ui) {
        super.populateUi(ui);
        if (ui instanceof CartoonPicturesMainUi) {
            populateCartoonPicturesMainUi((CartoonPicturesMainUi) ui);
        } else if (ui instanceof CartoonPicturesCategoryUi) {
            populateCartoonPicturesCategoryUiUi((CartoonPicturesCategoryUi) ui);
        }
    }

    private void populateCartoonPicturesMainUi(CartoonPicturesMainUi ui) {
        ui.setData(cartoonPicturesState.getCardInfos());
    }

    private void populateCartoonPicturesCategoryUiUi(CartoonPicturesCategoryUi ui) {
        CardInfo cardInfo = ((CartoonPicturesCategoryUi) ui).getCardInfo();
        ((CartoonPicturesCategoryUi) ui).setData(cartoonPicturesState.getSuCategoryInfos(cardInfo.getKey()));
    }

    @Subscribe
    public void onLoadingProgressVisibilityChanged(CartoonPicturesState.ShowLoadingProgressEvent event) {
        CartoonPicturesUi ui = (CartoonPicturesUi) findUi(event.mCallingId);
        if (ui == null || !(ui instanceof CartoonPicturesProgressUi)) {
            return;
        }

        if (event.secondary) {
            ((CartoonPicturesProgressUi) ui).showSecondaryLoadingProgress(event.show, event.hasMore);
        } else {
            ((CartoonPicturesProgressUi) ui).showLoadingProgress(event.show);
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
    public void onCartoonPicturesSuCategoryListChanged(CartoonPicturesState.CartoonPicturesSuCategoryListChanged
                                                               event) {
        CartoonPicturesSuCategoryUi ui = (CartoonPicturesSuCategoryUi) findUi(event.mCallingId);
        if (ui == null) {
            return;
        }

        ui.setData(event.pageResult);
    }

    @Subscribe
    public void onShowErrorEvent(CartoonPicturesState.ShowErrorEvent event) {
        CartoonPicturesUi ui = (CartoonPicturesUi) findUi(event.mCallingId);
        if (ui == null || !(ui instanceof CartoonPicturesProgressUi)) {
            return;
        }

        ((CartoonPicturesProgressUi) ui).showError(null);
    }


    @Override
    protected CartoonPicturesUiCallbacks createUiCallbacks(final CartoonPicturesUi ui) {
        return new CartoonPicturesUiCallbacks() {
            @Override
            public void fetchExpressionMain() {
                apiService.fetchExpressionMain(getId(ui));
            }

            @Override
            public void onErrorRetry() {
                if (ui instanceof CartoonPicturesMainUi) {
                    apiService.fetchExpressionMain(getId(ui));
                } else if (ui instanceof CartoonPicturesSuCategoryUi) {
                    apiService.fetchSuCategoryList(getId(ui), ((CartoonPicturesSuCategoryUi) ui).getSuCategoryInfo(), (
                            (CartoonPicturesSuCategoryUi) ui).getGifPageResult());
                }
            }

            @Override
            public void onGifItemClick(GifInfo gifInfo) {
                //展示弹出框
                ((BDisplay) getDisplay()).showGifDialogActivity(gifInfo);
            }

            @Override
            public void onCardItemMoreClick(CardInfo cardInfo) {
                ((BDisplay) getDisplay()).showCategoryActivity(cardInfo);
            }

            @Override
            public void fetchSuCategoryGifList(CategoryInfo suCategortInfo, GifPageResult pageResult) {
                if (!pageResult.hasNextPage()) {
                    return;
                }
                apiService.fetchSuCategoryList(getId(ui), suCategortInfo, pageResult);
            }

        };
    }

    public interface CartoonPicturesUiCallbacks {

        public void fetchExpressionMain();

        public void onErrorRetry();

        public void onGifItemClick(GifInfo gifInfo);

        public void onCardItemMoreClick(CardInfo cardInfo);

        public void fetchSuCategoryGifList(CategoryInfo suCategortInfo, GifPageResult pageResult);

    }

    public interface CartoonPicturesUi extends BaseUiController.Ui<CartoonPicturesUiCallbacks> {

    }

    public interface CartoonPicturesProgressUi extends CartoonPicturesUi {
        void showLoadingProgress(boolean visible);

        void showError(Exception e);

        void showSecondaryLoadingProgress(boolean visible, boolean hasMore);

        void showRefreshProgress(boolean successOrFail);
    }

    public interface CartoonPicturesMainUi extends CartoonPicturesProgressUi {
        void setData(List<CardInfo> data);
    }

    public interface CartoonPicturesCategoryUi extends CartoonPicturesUi {
        void setData(List<CategoryInfo> data);

        CardInfo getCardInfo();
    }

    public interface CartoonPicturesSuCategoryUi extends CartoonPicturesProgressUi {
        void setData(GifPageResult data);

        GifPageResult getGifPageResult();

        CategoryInfo getSuCategoryInfo();

    }

    public interface CartoonPicturesDetailUi extends CartoonPicturesProgressUi {

        void setData(List<ImageDetailInfo> data);

        String getUrl();
    }
}
