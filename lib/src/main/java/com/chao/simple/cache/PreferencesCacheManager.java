package com.chao.simple.cache;

/**
 * Created by yang2 on 2017/10/19.
 */

public class PreferencesCacheManager {
    private static PreferencesCacheManager mPreferencesCacheManager;
    private final PreferencesHttpCache mCacheManager;


    public static synchronized PreferencesCacheManager getInstance() {

        if (mPreferencesCacheManager == null) {

            mPreferencesCacheManager = new PreferencesCacheManager();

        }
        return mPreferencesCacheManager;

    }

    private PreferencesCacheManager() {
        mCacheManager = new PreferencesHttpCache();
    }

    public void saveCache(String key, String value) {

        mCacheManager.saveCache(key,value);

    }



    public String  getCache(String key) {
        return  mCacheManager.getCache(key);
    }
}
