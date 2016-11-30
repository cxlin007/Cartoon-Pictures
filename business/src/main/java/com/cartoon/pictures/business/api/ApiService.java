package com.cartoon.pictures.business.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by chenxunlin01 on 2016/11/14.
 */
public interface ApiService {

    @GET("http://www.3lian.com/gif/more/08/{page}")
    Call<ResponseBody> fetchImageList(@Path("page") String page);

    @GET("http://www.3lian.com/{url}")
    Call<ResponseBody> fetchImageDetail(@Path("url") String url);

    @GET("http://qq.yh31.com/")
    Call<ResponseBody> fetchExpressionMain();

}
