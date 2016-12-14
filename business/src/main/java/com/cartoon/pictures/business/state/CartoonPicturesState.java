package com.cartoon.pictures.business.state;

import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.business.bean.EmotionPageResult;
import com.cartoon.pictures.business.bean.GifPageResult;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
import com.squareup.otto.Bus;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class CartoonPicturesState {

    private Bus bus;
    public List<CardInfo> cardInfos;
    public HashMap<String, List<ImageDetailInfo>> details = new HashMap<>();
    private HashMap<String, List<CategoryInfo>> suCategoryInfos = new HashMap<>();

    public CartoonPicturesState() {
        this.bus = BusinessManager.getBus();
    }

    public List<CardInfo> getCardInfos() {
        return cardInfos;
    }

    public void setCardInfos(List<CardInfo> cardInfos) {
        this.cardInfos = cardInfos;
        bus.post(new CartoonPicturesMainDataChange());
    }

    public void addImageDetailInfos(String url, List<ImageDetailInfo> data) {
        details.put(url, data);
        bus.post(new CartoonPicturesMainDetailChange(url));
    }

    public List<ImageDetailInfo> getImageDetailInfos(String url) {
        return details.get(url);
    }

    public void putSuCategoryInfos(String key, List<CategoryInfo> categoryInfos) {
        suCategoryInfos.put(key, categoryInfos);
    }

    public List<CategoryInfo> getSuCategoryInfos(String key) {
        return suCategoryInfos.get(key);
    }

    public void setSuCategoryInfos(HashMap<String, List<CategoryInfo>> suCategoryInfos) {
        this.suCategoryInfos = suCategoryInfos;
    }

    public HashMap<String, List<CategoryInfo>> getSuCategoryInfos() {
        return suCategoryInfos;
    }

    public static class ShowLoadingProgressEvent {
        public final boolean show;
        public final boolean secondary;
        public final boolean hasMore;
        public final int mCallingId;

        public ShowLoadingProgressEvent(int callingId, boolean show) {
            this(callingId, show, false,false);
        }

        public ShowLoadingProgressEvent(int callingId, boolean show, boolean secondary,boolean hasMore) {
            this.mCallingId = callingId;
            this.show = show;
            this.secondary = secondary;
            this.hasMore = hasMore;
        }
    }

    public static class ShowErrorEvent {
        public final int mCallingId;

        public ShowErrorEvent(int mCallingId) {
            this.mCallingId = mCallingId;
        }
    }

    public static class CartoonPicturesMainDataChange {

    }

    public static class CartoonPicturesMainDetailChange {
        public final String url;

        public CartoonPicturesMainDetailChange(String url) {
            this.url = url;
        }
    }

    public static class CartoonPicturesSuCategoryListChanged {
        public final int mCallingId;
        public final GifPageResult pageResult;

        public CartoonPicturesSuCategoryListChanged(int mCallingId, GifPageResult pageResult) {
            this.mCallingId = mCallingId;
            this.pageResult = pageResult;
        }
    }

    public static class CartoonPicturesEmotionListChanged {
        public final int mCallingId;
        public final EmotionPageResult pageResult;

        public CartoonPicturesEmotionListChanged(int mCallingId, EmotionPageResult pageResult) {
            this.mCallingId = mCallingId;
            this.pageResult = pageResult;
        }
    }

    public static class CartoonPicturesSuEmotionListChanged {
        public final int mCallingId;
        public final GifPageResult pageResult;

        public CartoonPicturesSuEmotionListChanged(int mCallingId, GifPageResult pageResult) {
            this.mCallingId = mCallingId;
            this.pageResult = pageResult;
        }
    }

}
