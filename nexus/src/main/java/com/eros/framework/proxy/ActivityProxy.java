package com.eros.framework.proxy;

import android.app.Activity;

/**
 * Activity 代理类
 * Created by liuyuanxiao on 2018/5/31.
 */

public abstract class ActivityProxy {

    public abstract void onCreateInit(Activity activity);

    public abstract void onResume(Activity activity);

    public abstract void onStart(Activity activity);

    public abstract void onRestart(Activity activity);

    public abstract void onPause(Activity activity);

    public abstract void onStop(Activity activity);

    public abstract void onDestroy(Activity activity);

}
