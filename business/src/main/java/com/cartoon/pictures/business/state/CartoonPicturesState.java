package com.cartoon.pictures.business.state;

import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
import com.cartoon.pictures.business.bean.ImageInfo;
import com.cartoon.pictures.business.bean.PageInfo;
import com.catoon.corelibrary.EvnManager;
import com.squareup.otto.Bus;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class CartoonPicturesState {

    private Bus bus;
    public ImagePageInfo imagePageInfo;
    public HashMap<String, List<ImageDetailInfo>> details = new HashMap<>();

    public CartoonPicturesState() {
        this.bus = BusinessManager.getBus();
    }

    public ImagePageInfo getImagePageInfo() {
        return imagePageInfo;
    }

    public void setImagePageInfo(ImagePageInfo imagePageInfo) {
        this.imagePageInfo = imagePageInfo;
        bus.post(new CartoonPicturesMainDataChange());
    }

    public void addImageDetailInfos(String url, List<ImageDetailInfo> data) {
        details.put(url, data);
        bus.post(new CartoonPicturesMainDetailChange(url));
    }

    public List<ImageDetailInfo> getImageDetailInfos(String url) {
        return details.get(url);
    }

    public static class ImagePageInfo extends PageInfo<ImageInfo> {
    }


    public static class ShowLoadingProgressEvent {
        public final boolean show;
        public final boolean secondary;
        public final int mCallingId;

        public ShowLoadingProgressEvent(int callingId, boolean show) {
            this(callingId, show, false);
        }

        public ShowLoadingProgressEvent(int callingId, boolean show, boolean secondary) {
            this.mCallingId = callingId;
            this.show = show;
            this.secondary = secondary;
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
}
