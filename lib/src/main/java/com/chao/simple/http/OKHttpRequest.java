package com.chao.simple.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.chao.simple.cache.IHttpCache;
import com.chao.simple.Utils;
import com.chao.simple.cache.MemoryCacheManager;
import com.chao.simple.cache.PreferencesCacheManager;
import com.chao.simple.cache.PreferencesHttpCache;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yang2 on 2017/10/19.
 * okhttp引擎
 */

public class OKHttpRequest implements IHttpEngine {

    private OkHttpClient mClient;

    private IHttpCache mCache;
    private final Gson mGson;

    public OKHttpRequest() {
        mClient = new OkHttpClient();
        mGson = new Gson();

    }

    /**
     * @param context
     * @param url
     * @param params
     * @param callback
     * @param cache
     * @param <T>
     */
    @Override
    public <T> void get(Context context, final String url, Map<String, Object> params, final HttpCallBack<T> callback, final boolean cache) {
        String jointUrl = Utils.jointParams(url, params);

        final String memoryCacheJson = MemoryCacheManager.getInstance().getCache(jointUrl);

        final String preferenceCacheJson = PreferencesCacheManager.getInstance().getCache(jointUrl);
        //先从内存走
        if (cache && !TextUtils.isEmpty(memoryCacheJson)) {
            T result = (T) mGson.fromJson(memoryCacheJson, Utils.analysisClazzInfo(callback));
            callback.onSuccess(result);
        } else {
            //走缓存
            if (cache && !TextUtils.isEmpty(preferenceCacheJson)) {
                T result = (T) mGson.fromJson(preferenceCacheJson, Utils.analysisClazzInfo(callback));
                callback.onSuccess(result);
            }
        }



        /**
         * 缓存是先调用之后在去刷新
         */
        Request.Builder requestBuilder = new Request.Builder().url(jointUrl).tag(context);
        //可以省略，默认是GET请求
        Request request = requestBuilder.build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String resultJson = response.body().string();

                if (cache && resultJson.equals(memoryCacheJson)) {
                    return;
                }
                if (cache && resultJson.equals(preferenceCacheJson)) {
                    return;
                }

                T result = (T) mGson.fromJson(resultJson, Utils.analysisClazzInfo(callback));

                if (cache) {
                    MemoryCacheManager.getInstance().saveCache(url,resultJson);
                    PreferencesCacheManager.getInstance().saveCache(url,resultJson);
                }
            }
        });

    }

    /**
     * @param context
     * @param url
     * @param params
     * @param callback
     * @param cache
     * @param <T>
     */
    @Override
    public <T> void post(Context context, final String url, Map<String, Object> params, final HttpCallBack<T> callback, final boolean cache) {
        final String memoryCacheJson = MemoryCacheManager.getInstance().getCache(url);

        final String preferenceCacheJson = PreferencesCacheManager.getInstance().getCache(url);
        //先从内存走
        if (cache && !TextUtils.isEmpty(memoryCacheJson)) {
            T result = (T) mGson.fromJson(memoryCacheJson, Utils.analysisClazzInfo(callback));
            callback.onSuccess(result);
        } else {
            //走缓存
            if (cache && !TextUtils.isEmpty(preferenceCacheJson)) {
                T result = (T) mGson.fromJson(preferenceCacheJson, Utils.analysisClazzInfo(callback));
                callback.onSuccess(result);
            }
        }
        /**
         * 缓存是先调用之后在去刷新
         */

        FormBody.Builder builder = new FormBody.Builder();

        FormBody body = Utils.getBody(builder, params);

        Request.Builder requestBuilder = new Request.Builder().url(url).tag(context).post(body);

        Request request = requestBuilder.build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String resultJson = response.body().string();

                if (cache && resultJson.equals(memoryCacheJson)) {
                    return;
                }
                if (cache && resultJson.equals(preferenceCacheJson)) {
                    return;
                }

                T result = (T) mGson.fromJson(resultJson, Utils.analysisClazzInfo(callback));
                callback.onSuccess(result);
                if (cache) {
                    MemoryCacheManager.getInstance().saveCache(url,resultJson);
                    PreferencesCacheManager.getInstance().saveCache(url,resultJson);
                }
            }
        });
        /**
         * 缓存是先调用之后在去刷新最新的，这样体验会更好
         */

    }
}
