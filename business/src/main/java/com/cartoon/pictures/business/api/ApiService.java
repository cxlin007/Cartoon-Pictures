package com.cartoon.pictures.business.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public interface ApiService {

    @GET("http://qq.yh31.com/")
    Observable<ResponseBody> fetchExpressionMain();

    @GET("http://qq.yh31.com/{page}")
    Observable<ResponseBody> fetchSuCategoryList(@Path("page") String page);

}
