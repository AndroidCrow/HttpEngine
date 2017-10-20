package com.chao.httpengine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.chao.simple.http.HttpCallBack;
import com.chao.simple.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang2 on 2017/10/19.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Map<String,Object> map = new HashMap<>();
        map.put("phone","13666913334");

        map.put("password","000");

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtils.with(MainActivity.this).cache(true).get().url("http://192.168.1.122:8080/user/login2.do?").params(map)
                        .request(new HttpCallBack<Bean>() {


                            @Override
                            public void onSuccess(Bean result) {
                                Log.i("Tag",result.getStatus()+"===");
                            }

                            @Override
                            public void onFailure(Exception e) {
                                Log.i("Tag",e.getMessage());
                            }
                        });
            }
        });
        findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtils.with(MainActivity.this).cache(true).post().url("http://192.168.1.122:8080/user/login2.do?").params(map)
                        .request(new HttpCallBack<Bean>() {
                            @Override
                            public void onSuccess(Bean result) {
                                Log.i("Tag",result.getStatus()+"===");
                            }

                            @Override
                            public void onFailure(Exception e) {
                                Log.i("Tag",e.getMessage());
                            }
                        });
            }
        });
    }
}
