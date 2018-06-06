package com.benmu.framework.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;

import com.benmu.framework.BMWXEnvironment;

import com.benmu.framework.module.glide.GlideApp;
import com.bumptech.glide.RequestBuilder;

/**
 * 增加资源判断的Glide Created by Carry on 2017/9/13.
 */


public class BMHookGlide {

    private static final String LOCAL_SCHEME = "bmlocal";


    public static RequestBuilder<Drawable> load(Context context, String url) {

        return  GlideApp.with(context).load(handleResource(context, url));
    }

    private static String handleResource(Context context, String url) {
        if (TextUtils.isEmpty(url)) return "";
        Uri imageUri = Uri.parse(url);
        if (LOCAL_SCHEME.equalsIgnoreCase(imageUri.getScheme())) {
            return BMWXEnvironment.loadBmLocal(context, imageUri);
        }
        return url;
    }


}
