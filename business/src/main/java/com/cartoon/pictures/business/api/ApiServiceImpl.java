package com.cartoon.pictures.business.api;

import android.util.Log;

import com.cartoon.pictures.business.BusinessManager;
import com.cartoon.pictures.business.ProgressSubscriber;
import com.cartoon.pictures.business.bean.CardInfo;
import com.cartoon.pictures.business.bean.CategoryInfo;
import com.cartoon.pictures.business.bean.GifPageResult;
import com.cartoon.pictures.business.common.HtmlParseUtil;
import com.cartoon.pictures.business.state.CartoonPicturesState;
import com.squareup.otto.Bus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
        Observable<ResponseBody> call = serviceApi.fetchExpressionMain();
        final int page = 1;
        Log.e(TAG, "fetchExpressionMain: " + Thread.currentThread().getId());
        call.subscribeOn(Schedulers.io())
                .flatMap(new Func1<ResponseBody, Observable<List<CardInfo>>>() {
                    @Override
                    public Observable<List<CardInfo>> call(final ResponseBody responseBody) {
                        return Observable.create(new Observable.OnSubscribe<List<CardInfo>>() {

                            @Override
                            public void call(Subscriber<? super List<CardInfo>> subscriber) {
                                try {
                                    String str = new String(responseBody.bytes(), "UTF-8");
                                    Document document = Jsoup.parse(str);
                                    List<CardInfo> cardInfos = HtmlParseUtil.parseCardInfos(document);

                                    Log.e(TAG, "onResponse: " + cardInfos.toString());

                                    subscriber.onNext(cardInfos);
                                    subscriber.onCompleted();

                                } catch (IOException e) {
                                    subscriber.onError(e);
                                }
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<List<CardInfo>>(bus, callingId, page) {
                    @Override
                    public void onNext(List<CardInfo> cardInfos) {
                        super.onNext(cardInfos);
                        cartoonPicturesState.setCardInfos(cardInfos);
                    }
                });

    }

    public void fetchSuCategoryList(final int callingId, CategoryInfo categoryInfo, GifPageResult
            currPageResult) {
        if (currPageResult != null && !currPageResult.hasNextPage()) {
            return;
        }
        final int page = currPageResult == null ? 1 : currPageResult.nextPage();
        String path = "";
        if (page == 1) {
            path = categoryInfo.getKey();
        } else {
            path = categoryInfo.getKey() + "List_" + currPageResult.nextPage() + ".html";
        }
        Observable<ResponseBody> call = serviceApi.fetchSuCategoryList(path);
        call.subscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, GifPageResult>() {

                    @Override
                    public GifPageResult call(ResponseBody responseBody) {
                        String str = null;
                        try {
                            str = new String(responseBody.bytes(), "UTF-8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Document document = Jsoup.parse(str);
                        //解析分类
                        GifPageResult gifPageResult = HtmlParseUtil.parseGifInfoList(document);
                        gifPageResult.currPage = page;
                        Log.e(TAG, "onResponse: " + gifPageResult.toString());

                        return gifPageResult;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<GifPageResult>(bus, callingId, page) {
                    @Override
                    public void onNext(GifPageResult gifPageResult) {
                        super.onNext(gifPageResult);
                        bus.post(new CartoonPicturesState.CartoonPicturesSuCategoryListChanged(callingId,
                                gifPageResult));
                    }
                });
    }
}
