package com.cartoon.pictures.widget;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.Target;
import com.cartoon.pictures.glide.ProgressTarget;

/**
 * Created by chenxunlin01 on 2016/12/16.
 */

public class GifProgressTarget<Z> extends ProgressTarget<String, Z> {

    public static final String TAG = "GifProgressTarget";

    private final CircleProgressDrawable circleProgressDrawable;
    private final ImageView imageView;

    public GifProgressTarget(String model, Target<Z> target, ImageView imageView) {
        super(model, target);
        this.imageView = imageView;
        this.circleProgressDrawable = (CircleProgressDrawable) imageView.getDrawable();
    }

    @Override
    public float getGranualityPercentage() {
        return 10f;
    }

    @Override
    protected void onConnecting() {
    }

    @Override
    protected void onDownloading(long bytesRead, long expectedLength) {
        circleProgressDrawable.setProgress((int) (100 * bytesRead / expectedLength));
    }

    @Override
    protected void onDownloaded() {
        circleProgressDrawable.setProgress(100);
    }

    @Override
    protected void onDelivered() {
        imageView.setVisibility(View.INVISIBLE);
    }
}
