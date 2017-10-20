package com.chao.simple.http;

import android.content.Context;

import com.chao.simple.http.HttpCallBack;

import java.util.Map;

/**
 * Created by yang2 on 2017/10/19.
 * 请求引擎接口，如果想切换引擎只需继承此接口
 */

public interface IHttpEngine {

    <T> void get(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callback, final boolean cache);

    <T> void post(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callback, final boolean cache);

}
