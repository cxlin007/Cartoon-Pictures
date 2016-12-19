package com.cartoon.pictures.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.cartoon.pictures.glide.GlideManager;
import com.cartoon.pictures.R;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.downloader.CartoonDownloaderLinstener;
import com.cartoon.pictures.widget.CircleProgressDrawable;
import com.catoon.corelibrary.common.Utils;
import com.downloader.DownloaderManager;
import com.downloader.bean.DownloaderInfo;


public class GifDialogActivity extends BaseActivity {

    public static final String TAG = "GifDialogActivity";

    private ImageView progressWheel;
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
        final GifInfo gifInfo = getGifInfo();
        progressWheel = (ImageView) findViewById(R.id.item_progress_wheel);
        circleProgressDrawable = new CircleProgressDrawable();
        circleProgressDrawable.setProgress(10);
        progressWheel.setImageDrawable(circleProgressDrawable);

        final ImageView imageView = (ImageView) findViewById(R.id.gif_img);
        if (Utils.isGif(gifInfo.getRemoteUrl())) {
            GlideManager.loadGifWithProgress(this,gifInfo.getRemoteUrl(),imageView,progressWheel);

        } else {
            GlideManager.loadImageWithProgress(this,gifInfo.getRemoteUrl(),imageView,progressWheel);
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

}
