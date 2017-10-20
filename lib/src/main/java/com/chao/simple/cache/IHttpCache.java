package com.chao.simple.cache;

/**
 * Created by yang2 on 2017/10/19.
 */

public interface IHttpCache {

    String getCache(String key);

    void saveCache(String key,String value);
}
