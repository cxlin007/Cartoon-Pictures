package com.cartoon.pictures.business.api;

import android.util.Log;

import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.business.bean.GifInfo;
import com.cartoon.pictures.business.bean.GifPageResult;
import com.cartoon.pictures.business.bean.ImageDetailInfo;
import com.cartoon.pictures.business.bean.ImageInfo;
import com.cartoon.pictures.business.common.Constants;
import com.cartoon.pictures.business.common.HtmlParseUtil;
import com.cartoon.pictures.business.state.CartoonPicturesState;
import com.catoon.corelibrary.common.Utils;
import com.squareup.otto.Bus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void fetchExpressionMain(final int callingId) {
        bus.post(createPageLoadingProgressEvent(callingId, true,1));
        Call<ResponseBody> call = serviceApi.fetchExpressionMain();
        call.enqueue(new ACallback<ResponseBody>(bus, 1, callingId) {

            @Override
            protected void doResponse(Call<ResponseBody> call, Response<ResponseBody> response) throws Exception {
                String str = new String(response.body().bytes(), "UTF-8");


                Document document = Jsoup.parse(str);
                List<CardInfo> cardInfos = HtmlParseUtil.parseCardInfos(document);
                Log.e(TAG, "onResponse: " + cardInfos.toString());
                cartoonPicturesState.setCardInfos(cardInfos);
            }
        });
    }

    public void fetchCategory(final int callingId, CardInfo cardInfo) {
        bus.post(createPageLoadingProgressEvent(callingId, true,1));
        Call<ResponseBody> call = serviceApi.fetchCategory(cardInfo.getKey());
        call.enqueue(new ACallback<ResponseBody>(bus, 1, callingId) {

            @Override
            protected void doResponse(Call<ResponseBody> call, Response<ResponseBody> response) throws Exception {
                String str = new String(response.body().bytes(), "UTF-8");

                Document document = Jsoup.parse(str);
                //解析分类
                HtmlParseUtil.parseSuCategoryInfos(cartoonPicturesState.getSuCategoryInfos(), document);
                bus.post(new CartoonPicturesState.CartoonPicturesCategoryListChanged(callingId));
            }
        });
    }

    public void fetchSuCategoryList(final int callingId, CategoryInfo categoryInfo, GifPageResult
            currPageResult) {
        if (currPageResult != null && !currPageResult.hasNextPage()) {
            return;
        }
        final int nextPage = currPageResult == null ? 1 : currPageResult.nextPage();
        String path = "";
        if (nextPage == 1) {
            path = categoryInfo.getKey();
        } else {
            path = categoryInfo.getKey() + "List_" + currPageResult.nextPage() + ".html";
        }

        bus.post(createPageLoadingProgressEvent(callingId, true,nextPage));
        Call<ResponseBody> call = serviceApi.fetchSuCategoryList(path);
        call.enqueue(new ACallback<ResponseBody>(bus, nextPage, callingId) {

            @Override
            protected void doResponse(Call<ResponseBody> call, Response<ResponseBody> response) throws Exception {
                String str = new String(response.body().bytes(), "UTF-8");

                Document document = Jsoup.parse(str);
                //解析分类
                GifPageResult gifPageResult = HtmlParseUtil.parseGifInfoList(document);
                gifPageResult.currPage = nextPage;
                Log.e(TAG, "onResponse: " + gifPageResult.toString());
                bus.post(new CartoonPicturesState.CartoonPicturesSuCategoryListChanged(callingId, gifPageResult));

            }
        });
    }

    private Object createPageLoadingProgressEvent(int callingId, boolean show, int page) {
        if (page > 1) {
            return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show, true);
        } else {
            return new CartoonPicturesState.ShowLoadingProgressEvent(callingId, show);
        }
    }

}
