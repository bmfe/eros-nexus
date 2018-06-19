package com.benmu.framework.module.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import java.io.InputStream;
import okhttp3.OkHttpClient;

@GlideModule
public class UltraGlideModule extends AppGlideModule {
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull
            Registry registry) {
        super.registerComponents(context, glide, registry);
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new
                OkHttpClient.Builder().addNetworkInterceptor(ProgressManager.init()).build()));
    }
}
