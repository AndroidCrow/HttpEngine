package com.chao.httpengine;

import android.app.Application;

import com.chao.simple.cache.PreferencesUtil;
import com.chao.simple.http.HttpUtils;
import com.chao.simple.http.OKHttpRequest;

/**
 * Created by yang2 on 2017/10/19.
 */

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtils.init(new OKHttpRequest());
        PreferencesUtil.getInstance().init(this);
    }
}
