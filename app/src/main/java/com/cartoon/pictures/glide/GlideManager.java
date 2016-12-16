package com.cartoon.pictures.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by chenxunlin01 on 2016/12/16.
 */

public class GlideManager {

    private static GlideManager glideManager;
    private ConcurrentHashMap<String, List<GlideListener>> listenerMap = new ConcurrentHashMap();

    public ConcurrentHashMap<String, List<GlideListener>> getListenerMap() {
        return listenerMap;
    }

    public static void addGlideListener(String url, GlideListener listener) {
        if (glideManager == null) {
            return;
        }
        ConcurrentHashMap<String, List<GlideListener>> listenerMap = glideManager.getListenerMap();

        List<GlideListener> list = listenerMap.get(url);
        if (list == null) {
            list = Collections.synchronizedList(new ArrayList<GlideListener>());
            listenerMap.put(url, list);
        }
        list.add(listener);
    }

    public static void removeGlideListener(String url, GlideListener listener) {
        if (glideManager == null) {
            return;
        }
        ConcurrentHashMap<String, List<GlideListener>> listenerMap = glideManager.getListenerMap();
        List<GlideListener> list = listenerMap.get(url);
        if (list != null) {
            list.remove(listener);
        }
    }

    public static void init(Context context) {
        if (glideManager == null) {
            glideManager = new GlideManager();
        }
        OkHttpClient okHttpClient = glideManager.createOkHttpClient();
        Glide.get(context).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(okHttpClient));
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(12, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final String url = chain.request().url().toString();
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(),
                                new ResponseLinstener(url))).build();
                    }
                })
                .build();
        return client;
    }

    private static class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final ResponseLinstener responseLinstener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, ResponseLinstener responseLinstener) {
            this.responseBody = responseBody;
            this.responseLinstener = responseLinstener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    responseLinstener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    return bytesRead;
                }
            };
        }
    }

    private class ResponseLinstener {

        private final String url;

        public ResponseLinstener(String url) {
            this.url = url;
        }

        public void update(long bytesRead, long contentLength, boolean done) {
            List<GlideListener> listeners = listenerMap.get(url);
            if (listeners != null) {
                for (GlideListener listener : listeners) {
                    listener.update(bytesRead, contentLength, done);
                }
            }
        }
    }

    public interface GlideListener {
        void update(long bytesRead, long contentLength, boolean done);
    }

}
