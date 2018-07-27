package com.benmu.framework.http.okhttp.params;

import android.text.TextUtils;

import com.benmu.framework.BMWXEnvironment;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor  implements Interceptor {

    public static final String TAG = "OkHttpHeader";
    private String tag;

    public HeaderInterceptor(String tag)
    {
        if (TextUtils.isEmpty(tag))
        {
            tag = TAG;
        }
        this.tag = tag;
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        request = addForRequest(request);
        Response response = chain.proceed(request);
        return response;
    }

    private Request addForRequest(Request request)
    {
        try
        {
            request = request.newBuilder()
                    //这个的话内容有点多啊，大家记住这么写就是只从缓存取，想要了解这个东西我等下在
                    // 给大家写连接吧。大家可以去看下，获取大家去找拦截器资料的时候就可以看到这个方面的东西反正也就是缓存策略。
//                    .cacheControl(CacheControl.FORCE_CACHE)
                    .header("app_id", BMWXEnvironment.mPlatformConfig.getAppName())
                    .build();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return request;
    }


}
