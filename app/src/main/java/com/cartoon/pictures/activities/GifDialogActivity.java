package com.cartoon.pictures.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cartoon.pictures.R;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.uilibrary.widget.ProgressWheel;
import com.catoon.corelibrary.controllers.BaseUiController;
import com.downloader.DownloaderManager;
import com.downloader.IDownloaderLinstener;
import com.downloader.bean.DownloaderInfo;


public class GifDialogActivity extends BaseActivity implements IDownloaderLinstener {

    public static final String TAG = "GifDialogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_dialog);
        final GifInfo gifInfo = getGifInfo();
        if (gifInfo == null) {
            finish();
            return;
        }

        bindDatas();
    }

    private void bindDatas() {
        ImageView imageView = (ImageView) findViewById(R.id.gif_img);
        final ProgressWheel progressWheel = (com.cartoon.pictures.uilibrary.widget.ProgressWheel) findViewById(R.id
                .item_progress_wheel);
        final View imgLayout = findViewById(R.id.img_layout);
        final GifInfo gifInfo = getGifInfo();
        Glide.with(this).load(gifInfo.getRemoteUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {

                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean
                            isFirstResource) {
                        progressWheel.setVisibility(View.GONE);
                        imgLayout.setVisibility(View.INVISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        progressWheel.setVisibility(View.GONE);
                        imgLayout.setVisibility(View.VISIBLE);
                        return false;
                    }
                }).into
                (imageView);

        Button downBtn = (Button) findViewById(R.id.downloader);
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloaderInfo downloaderInfo = new DownloaderInfo();
                downloaderInfo.setId(gifInfo.getRemoteUrl().hashCode() + "");
                downloaderInfo.setUrl(gifInfo.getRemoteUrl());
                DownloaderManager.instance().start(downloaderInfo, GifDialogActivity.this);
            }
        });
    }

    private GifInfo getGifInfo() {
        return (GifInfo) getIntent().getSerializableExtra("gif");
    }

    @Override
    public void downloaderPause() {
        Log.e(TAG, "downloaderPause: ");
    }

    @Override
    public void downloaderError(Exception ex) {
        Log.e(TAG, "downloaderError: ");
    }

    @Override
    public void downloaderSuccess() {
        Log.e(TAG, "downloaderSuccess: ");
    }

    @Override
    public void downloaderProgressChange(int progress) {
        Log.e(TAG, "downloaderProgressChange: " + progress);
    }
}
