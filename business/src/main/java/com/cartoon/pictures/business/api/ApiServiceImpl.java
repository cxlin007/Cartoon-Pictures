package com.cartoon.pictures.business.api;

import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.bean.ImageInfo;
import com.cartoon.pictures.business.common.HtmlParseUtil;
import com.cartoon.pictures.business.state.CartoonPicturesState;
import com.squareup.otto.Bus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
        if (pageIndex < 0) {
            return;
        }

        bus.post(createLoadingProgressEvent(callingId, true, pageIndex));

        String page = "";
        if (pageIndex == 0) {
            page = "";
        } else {
            page = "index_" + (page + 2) + ".html";
        }

        Call<ResponseBody> call = serviceApi.fetchImageList(page);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Document document = Jsoup.parse(new String(response.body().bytes(), "GBK"));
                    List<ImageInfo> imgeInfos = HtmlParseUtil.parseMainImageInfos(document);
                    int totalPage = HtmlParseUtil.parseMainTotalPage(document);
                    CartoonPicturesState.ImagePageInfo imagePageInfo = cartoonPicturesState.getImagePageInfo();
                    if (imagePageInfo == null) {
                        imagePageInfo = new CartoonPicturesState.ImagePageInfo();
                    }
                    imagePageInfo.addData(imgeInfos);
                    imagePageInfo.setCurrPage(pageIndex);
                    imagePageInfo.setTotalPage(totalPage);
                    cartoonPicturesState.setImagePageInfo(imagePageInfo);
                    bus.post(createLoadingProgressEvent(callingId, false, pageIndex));
                } catch (Exception e) {
                    e.printStackTrace();
                    handleError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                handleError();
            }

            private void handleError() {
                bus.post(createLoadingProgressEvent(callingId, false, pageIndex));
                bus.post(new CartoonPicturesState.ShowErrorEvent(callingId));
            }
        });
    }


    private Object createLoadingProgressEvent(int callingId, boolean show, int page) {
        if (page > 0) {
            return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show, true);
        } else {
            return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show);
        }
    }

}
