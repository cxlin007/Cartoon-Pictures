package com.cartoon.pictures.downloader;

import android.content.Context;
import android.widget.Toast;

import com.cartoon.pictures.MainApplication;
import com.cartoon.pictures.R;
import com.cartoon.pictures.business.bean.GifInfo;
import com.downloader.DownloadException;
import com.downloader.IDownloaderLinstener;

/**
 * Created by chenxunlin01 on 2016/12/12.
 */

public class CartoonDownloaderLinstener implements IDownloaderLinstener {

    private final GifInfo gifInfo;
    private final Context mContext;
    private boolean isToastLoading = false;

    public CartoonDownloaderLinstener(GifInfo gifInfo, Context context) {
        this.gifInfo = gifInfo;
        this.mContext = context;
    }

    @Override
    public void downloaderPause() {

    }

    @Override
    public void downloaderError(DownloadException ex) {
        if(ex.getHttpCode() == 416){
            Toast.makeText(mContext, R.string.downloadered_toast, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext, R.string.downloader_error_toast, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void downloaderSuccess() {
        Toast.makeText(mContext, mContext.getString(R.string.downloader_success_toast, MainApplication
                .DOWNLOADER_DIR), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void downloaderProgressChange(int progress) {
        if(!isToastLoading){
            Toast.makeText(mContext, R.string.downloader_doing_toast, Toast.LENGTH_SHORT).show();
            isToastLoading = false;
        }
    }
}
