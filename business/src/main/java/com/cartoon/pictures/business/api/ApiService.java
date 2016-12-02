package com.cartoon.pictures.business.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public interface ApiService {

    @GET("http://qq.yh31.com/")
    Call<ResponseBody> fetchExpressionMain();

    @GET("http://qq.yh31.com/{page}")
    Call<ResponseBody> fetchSuCategoryList(@Path("page") String page);

    @GET("http://qq.yh31.com/{page}")
    Call<ResponseBody> fetchCategory(@Path("page") String page);

}
