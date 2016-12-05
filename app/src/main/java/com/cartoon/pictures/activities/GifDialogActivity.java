package com.cartoon.pictures.activities;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cartoon.pictures.R;
import com.cartoon.pictures.adapters.CardAdapter;
import com.cartoon.pictures.base.BaseActivity;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.controllers.CartoonPicturesController;
import com.cartoon.pictures.uilibrary.widget.MProgressView;
import com.cartoon.pictures.uilibrary.widget.ProgressWheel;
import com.cartoon.pictures.widget.CommonListView;
import com.catoon.corelibrary.common.Utils;
import com.catoon.corelibrary.controllers.BaseUiController;

import java.util.List;

public class GifDialogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_dialog);

        GifInfo gifInfo = (GifInfo) getIntent().getSerializableExtra("gif");
        if (gifInfo == null) {
            finish();
            return;
        }

        ImageView imageView = (ImageView) findViewById(R.id.gif_img);
        final ProgressWheel progressWheel = (com.cartoon.pictures.uilibrary.widget.ProgressWheel) findViewById(R.id
                .item_progress_wheel);
        Glide.with(this).load(gifInfo.getRemoteUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {

                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean
                            isFirstResource) {
                        progressWheel.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        progressWheel.setVisibility(View.GONE);
                        return false;
                    }
                }).into
                (imageView);
    }

    @Override
    protected BaseUiController getController() {
        return null;
    }

}
