package com.catoon.corelibrary;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/11/13.
 */

public class HtmlConverterFactory extends Converter.Factory {

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (!(type instanceof Class<?>)) {
            return null;
        }

        return new StringRequestConverter();

//        for (Annotation annotation : methodAnnotations) {
//            if (annotation instanceof TypeString) {
//                return new StringRequestConverter();
//            }
//        }
//        return null;
    }

    public static HtmlConverterFactory create(){
        return new HtmlConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (!(type instanceof Class<?>)) {
            return null;
        }

        return new StringResponseConverter();
//        for (Annotation annotation : annotations) {
//            if (annotation instanceof TypeString) {
//                return new StringResponseConverter();
//            }
//        }
//        return null;
    }

    public static class StringRequestConverter implements Converter<String, RequestBody> {

        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

        @Override
        public RequestBody convert(String value) throws IOException {
            return RequestBody.create(MEDIA_TYPE, value);
        }
    }

    public static class StringResponseConverter implements Converter<ResponseBody, String> {

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }
}
