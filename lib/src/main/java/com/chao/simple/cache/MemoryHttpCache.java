package com.chao.simple.cache;

import android.support.v4.util.LruCache;

/**
 * Created by yang2 on 2017/10/19.
 * 内存缓存
 */

public class MemoryHttpCache implements IHttpCache {

    private final static int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024) / 8;

    private final static LruCache<String, Object> mMemoryCache = new LruCache<>(cacheSize);

    @Override
    public String getCache(String key) {
        return (String) mMemoryCache.get(key);
    }

    @Override
    public void saveCache(String key, String value) {
        mMemoryCache.put(key, value);
    }
}
