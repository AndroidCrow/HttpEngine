package com.chao.simple.http;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang2 on 2017/10/19.
 * http工具类
 */

public class HttpUtils {

    private static IHttpEngine mHttpEngine;

    private Context mContext;

    private String mUrl;

    private boolean mCache;

    private int mType;

    private final int TYPE_GET = 0x0001;

    private final int TYPE_POST =0x0002;

    private Map<String,Object> mParams = new HashMap<>();



    private HttpUtils(Context context){
        this.mContext = context;
    }

    public static HttpUtils with(Context context){
        return new HttpUtils(context);
    }

    public HttpUtils url(String url){
        this.mUrl = url;
        return this;
    }


    public HttpUtils cache(boolean cache){
        this.mCache = cache;

        return this;
    }


    public HttpUtils get(){
        this.mType = TYPE_GET;
        return this;
    }

    public HttpUtils post(){
        this.mType = TYPE_POST;
        return this;
    }

    public HttpUtils param(String key, String valus){
        this.mParams.put(key,valus);

        return this;
    }
    public HttpUtils params(Map<String,Object> params){
        this.mParams.putAll(params);

        return this;
    }
    public <T> void request(HttpCallBack<T> callBack){
        //异常处理
        if (mHttpEngine == null){
            new NullPointerException("The engin cannot be null");
        }

        if (mUrl == null){
            new NullPointerException("The url cannot be null");
        }
        if (mType == 0){
            mType = TYPE_GET;
        }

        if (mType == TYPE_GET){
            mHttpEngine.get(mContext,mUrl,mParams,callBack,mCache);
        }else {
            mHttpEngine.post(mContext,mUrl,mParams,callBack,mCache);
        }


    }

//    public String request(){
//        //异常处理
//        if (mHttpEngine == null){
//            new NullPointerException("The engin cannot be null");
//        }
//
//        if (mUrl == null){
//            new NullPointerException("The url cannot be null");
//        }
//        if (mType == 0){
//            mType = TYPE_GET;
//        }
//
//
//    }

    public static void init(IHttpEngine httpEngine) {

        mHttpEngine = httpEngine;

    }
}
