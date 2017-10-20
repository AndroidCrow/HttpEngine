package com.chao.simple.cache;

/**
 * Created by yang2 on 2017/10/19.
 * 文件缓存
 */
public class PreferencesHttpCache implements IHttpCache{
    /**
     * 获取数据
     */
    @Override
    public String getCache(String key) {
        return (String) PreferencesUtil.getInstance().getParam(key, "");
    }
    /**
     * 缓存数据
     */
    @Override
    public void saveCache(String key, String value) {
        PreferencesUtil.getInstance().saveParam(key, value);
    }
}
