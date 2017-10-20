#### 扩展性强，三级缓存功能使用方便一行代码搞定
#### 本来想用数据库做二级缓存，暂时没什么思路所以先用文件缓存替代一下
#### 引擎使用接口来管理，需要切换okhttp或者retrofit等第三方请求框架时实现IHttpEngine即可

```

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtils.init(new OKHttpRequest());
        PreferencesUtil.getInstance().init(this);
    }
}

OKHttpRequest implements IHttpEngine


public interface IHttpEngine {

    <T> void get(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callback, final boolean cache);

    <T> void post(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callback, final boolean cache);

}



```
#### 其他的不说下面直接上使用方法

```
 HttpUtils.with(MainActivity.this).cache(true).get().url("http://114.67.129.197:8080/mmall/user/login2.do?").params(map)
 //Object直接使用对象就行
    .request(new HttpCallBack<Object>() {
                        
                        
    });
```

