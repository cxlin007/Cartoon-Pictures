package com.cartoon.pictures.widget;

import com.bumptech.glide.request.target.Target;
import com.cartoon.pictures.glide.ProgressTarget;

/**
 * Created by chenxunlin01 on 2016/12/16.
 */

public class GifProgressTarget<Z> extends ProgressTarget<String,Z> {

    public GifProgressTarget(Target<Z> target) {
        super(target);
    }

    @Override
    protected void onConnecting() {

    }

    @Override
    protected void onDownloading(long bytesRead, long expectedLength) {

    }

    @Override
    protected void onDownloaded() {

    }

    @Override
    protected void onDelivered() {

    }
}
