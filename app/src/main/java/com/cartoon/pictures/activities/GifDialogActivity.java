package com.cartoon.pictures.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cartoon.pictures.glide.OkHttpProgressGlideModule;
import com.cartoon.pictures.R;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.downloader.CartoonDownloaderLinstener;
import com.cartoon.pictures.widget.CircleProgressDrawable;
import com.catoon.corelibrary.common.Utils;
import com.downloader.DownloaderManager;
import com.downloader.bean.DownloaderInfo;


public class GifDialogActivity extends BaseActivity implements OkHttpProgressGlideModule.UIProgressListener {

    public static final String TAG = "GifDialogActivity";

    private TextView progressWheel;
    private CircleProgressDrawable circleProgressDrawable;

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

//        final ProgressWheel progressWheel = (com.cartoon.pictures.uilibrary.widget.ProgressWheel) findViewById(R.id
//                .item_progress_wheel);
        progressWheel = (TextView) findViewById(R.id.item_progress_wheel);
//        circleProgressDrawable = new CircleProgressDrawable();
//        circleProgressDrawable.setProgress(0);
//        progressWheel.setImageDrawable(circleProgressDrawable);

        final View imgLayout = findViewById(R.id.img_layout);
        final GifInfo gifInfo = getGifInfo();
        ImageView imageView = (ImageView) findViewById(R.id.gif_img);
        if (Utils.isGif(gifInfo.getRemoteUrl())) {
            Glide.with(this).load(gifInfo.getRemoteUrl()).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .listener(new RequestListener<String, GifDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean
                                isFirstResource) {
                            progressWheel.setVisibility(View.GONE);
                            imgLayout.setVisibility(View.INVISIBLE);

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable>
                                target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressWheel.setVisibility(View.GONE);
                            imgLayout.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    .into(imageView);
        } else {
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
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable>
                                target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressWheel.setVisibility(View.GONE);
                            imgLayout.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    .into(imageView);
        }
        ImageView downBtn = (ImageView) findViewById(R.id.download);
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloaderInfo downloaderInfo = new DownloaderInfo();
                downloaderInfo.setId(gifInfo.getRemoteUrl().hashCode() + "");
                downloaderInfo.setUrl(gifInfo.getRemoteUrl());
                DownloaderManager.instance().start(downloaderInfo, new CartoonDownloaderLinstener(gifInfo, imContext));
            }
        });
    }

    private GifInfo getGifInfo() {
        return (GifInfo) getIntent().getSerializableExtra("gif");
    }

//    @Override
//    public void update(long bytesRead, long contentLength, boolean done) {
//        Log.e(TAG, "update: " + bytesRead * 100 / contentLength);
////        circleProgressDrawable.setProgress((int) (bytesRead * 100 / contentLength));
//        progressWheel.setText(""+bytesRead * 100 / contentLength);
//    }

    @Override
    public void onProgress(long bytesRead, long expectedLength) {
        Log.e(TAG, "update: " + bytesRead * 100 / expectedLength);
    }

    @Override
    public float getGranualityPercentage() {
        return 10;
    }
}
