package com.cartoon.pictures.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.cartoon.pictures.widget.GifProgressTarget;

/**
 * Created by chenxunlin01 on 2016/12/16.
 */

public class GlideManager {

    private static GlideManager glideManager;

    public static void loadGifWithProgress(Context context, String url, final ImageView imageView, final ImageView progressView) {
        Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .into(new GifProgressTarget<GifDrawable>(url, new ViewTarget<ImageView, GifDrawable>
                        (imageView) {
                    @Override
                    public void onResourceReady(final GifDrawable resource, GlideAnimation<? super GifDrawable>
                            glideAnimation) {
                        imageView.setImageDrawable(resource);
                        resource.start();
                    }
                }, progressView));
    }

    public static void loadImageWithProgress(Context context, String url, final ImageView imageView, final ImageView progressView) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .into(new GifProgressTarget<GlideDrawable>(url, new ViewTarget<ImageView, GlideDrawable>
                        (imageView) {

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable>
                            glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                }, progressView));
    }

}
