package com.eros.framework.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.eros.R;
import com.eros.framework.BMWXEnvironment;

import com.eros.framework.module.glide.GlideApp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * 增加资源判断的Glide Created by Carry on 2017/9/13.
 */


public class BMHookGlide implements ImageLoader {

    private static final String LOCAL_SCHEME = "bmlocal";


    public static RequestBuilder<Drawable> load(Context context, String url) {

        return GlideApp.with(context).load(handleResource(context, url));
    }

    private static String handleResource(Context context, String url) {
        if (TextUtils.isEmpty(url)) return "";
        Uri imageUri = Uri.parse(url);
        if (LOCAL_SCHEME.equalsIgnoreCase(imageUri.getScheme())) {
            return BMWXEnvironment.loadBmLocal(context, imageUri);
        }
        return url;
    }


    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int
            height) {
        Log.e("GlideImageLoader", "GlideImageLoader>>>>>>" + path);
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path))).apply(new RequestOptions().error(R.drawable
                .ic_default_image).placeholder(R.drawable.ic_default_image).diskCacheStrategy
                (DiskCacheStrategy.ALL))   //设置图片路径(fix #8,
                // 文件名包含%符号 无法识别和显示)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int
            width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path))).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))  //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }


}
