package com.cartoon.pictures.module;

import com.cartoon.pictures.module.annotations.TypeString;
import com.cartoon.pictures.module.bean.ImageInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/13.
 */

public interface ServiceApi {

    @TypeString
    @GET("http://www.3lian.com/gif/more/08/")
    Call<String> fetchImageList();
}
