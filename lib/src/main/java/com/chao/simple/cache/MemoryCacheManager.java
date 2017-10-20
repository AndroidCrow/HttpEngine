package com.chao.simple.cache;

import android.support.v4.util.LruCache;

/**
 * Created by yang2 on 2017/10/19.
 * 内存缓存管理类
 */

public class MemoryCacheManager {

    private static MemoryCacheManager mMemoryCacheManager = null;

    private final MemoryHttpCache mMemoryHttpCache;


    public static synchronized MemoryCacheManager getInstance() {

        if (mMemoryCacheManager == null) {

            mMemoryCacheManager = new MemoryCacheManager();

        }
        return mMemoryCacheManager;

    }


    private MemoryCacheManager() {
        mMemoryHttpCache = new MemoryHttpCache();
    }


    public void saveCache(String key, String value) {

        mMemoryHttpCache.saveCache(key,value);

    }



    public String  getCache(String key) {
        return  mMemoryHttpCache.getCache(key);
    }
}
