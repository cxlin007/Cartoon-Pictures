package com.cartoon.pictures.business.api;

import android.util.Log;

import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
import com.cartoon.pictures.business.bean.ImageInfo;
import com.cartoon.pictures.business.common.HtmlParseUtil;
import com.cartoon.pictures.business.state.CartoonPicturesState;
import com.squareup.otto.Bus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/11/13.
 */

public class ApiServiceImpl {

    private static final String TAG = "ServiceApiImpl";

    private ApiService serviceApi;
    private CartoonPicturesState cartoonPicturesState;
    private Bus bus;

    public ApiServiceImpl() {
        cartoonPicturesState = BusinessManager.getCartoonPicturesState();
        serviceApi = BusinessManager.getRetrofitManager().create(ApiService.class);
        bus = BusinessManager.getBus();
    }


    public void fetchImageList(final int callingId, final int pageIndex) {
        Log.e(TAG, "fetchImageList: " + pageIndex);
        if (pageIndex < 0) {
            return;
        }

        bus.post(createPageLoadingProgressEvent(callingId, true, pageIndex));

        String page = "";
        if (pageIndex == 0) {
            page = "";
        } else {
            page = "index_" + (pageIndex + 2) + ".html";
        }

        Call<ResponseBody> call = serviceApi.fetchImageList(page);
        call.enqueue(new ACallback<ResponseBody>() {
            @Override
            protected void doResponse(Call<ResponseBody> call, Response<ResponseBody> response) throws Exception {
                String str = new String(response.body().bytes(), "GBK");
                Document document = Jsoup.parse(str);
                List<ImageInfo> imgeInfos = HtmlParseUtil.parseMainImageInfos(document);
                Log.e(TAG, "onResponse: " + imgeInfos.toString());
                int totalPage = HtmlParseUtil.parseMainTotalPage(document);
                CartoonPicturesState.ImagePageInfo imagePageInfo = cartoonPicturesState.getImagePageInfo();
                if (imagePageInfo == null) {
                    imagePageInfo = new CartoonPicturesState.ImagePageInfo();
                }
                imagePageInfo.addData(imgeInfos);
                imagePageInfo.setCurrPage(pageIndex);
                imagePageInfo.setTotalPage(totalPage);
                cartoonPicturesState.setImagePageInfo(imagePageInfo);
            }

            @Override
            protected void doFailure(Call<ResponseBody> call, Throwable t) {
                handleError();
            }

            @Override
            protected void doFinish() {
                bus.post(createPageLoadingProgressEvent(callingId, false, pageIndex));
            }

            private void handleError() {
                bus.post(createPageLoadingProgressEvent(callingId, false, pageIndex));
                if (pageIndex == 0) {
                    bus.post(new CartoonPicturesState.ShowErrorEvent(callingId));
                }
            }
        });
    }

    public void fetchImageDetail(final int callingId, final String url) {
        bus.post(createLoadingProgressEvent(callingId, true));

        Call<ResponseBody> call = serviceApi.fetchImageDetail(url);
        call.enqueue(new ACallback<ResponseBody>() {

            @Override
            protected void doResponse(Call<ResponseBody> call, Response<ResponseBody> response) throws Exception {
                String str = new String(response.body().bytes(), "GBK");
                Document document = Jsoup.parse(str);
                List<ImageDetailInfo> imgeInfos = HtmlParseUtil.parseDetailImageInfos(document);
                Log.e(TAG, "onResponse: " + imgeInfos.toString());
                cartoonPicturesState.addImageDetailInfos(url, imgeInfos);
            }

            @Override
            protected void doFailure(Call<ResponseBody> call, Throwable t) {
                handleError();
            }

            @Override
            protected void doFinish() {
                bus.post(createLoadingProgressEvent(callingId, false));
            }

            private void handleError() {
                bus.post(createLoadingProgressEvent(callingId, false));
                bus.post(new CartoonPicturesState.ShowErrorEvent(callingId));
            }
        });
    }


    private Object createPageLoadingProgressEvent(int callingId, boolean show, int page) {
        if (page > 0) {
            return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show, true);
        } else {
            return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show);
        }
    }

    private Object createLoadingProgressEvent(int callingId, boolean show) {
        return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show);
    }

}
