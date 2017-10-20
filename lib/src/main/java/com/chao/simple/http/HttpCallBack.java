package com.chao.simple.http;

/**
 * Created by yang2 on 2017/10/19.
 * 请求统一回调
 */
public abstract class HttpCallBack<T>{

    // 返回可以直接操作的对象
    public abstract void onSuccess(T result);

    public abstract void onFailure(Exception e);
}
