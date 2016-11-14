package com.cartoon.pictures.business.state;

import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.bean.ImageInfo;
import com.cartoon.pictures.business.bean.PageInfo;
import com.catoon.corelibrary.EvnManager;
import com.squareup.otto.Bus;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public class CartoonPicturesState {

    private Bus bus;
    public ImagePageInfo imagePageInfo;

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
}
