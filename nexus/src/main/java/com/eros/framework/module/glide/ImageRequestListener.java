package com.eros.framework.module.glide;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public abstract class ImageRequestListener implements RequestListener, OnProgressListener {
    private boolean mLoaded;

    public ImageRequestListener() {
        ProgressManager.addProgressListener(this);
    }


    @Override
    public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone,
                           Exception exception) {
        if (!mLoaded) {
            onLoading(imageUrl, bytesRead, totalBytes, isDone, exception);
        }
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean
            isFirstResource) {
        mLoaded = true;
        onFailed(e, model, target, isFirstResource);
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, DataSource
            dataSource, boolean isFirstResource) {
        mLoaded = true;
        onReady(resource, model, target, dataSource, isFirstResource);
        return false;
    }

    public abstract void onReady(Object resource, Object model, Target target, DataSource
            dataSource, boolean isFirstResource);

    public abstract void onFailed(GlideException e, Object model, Target target, boolean
            isFirstResource);

    public abstract void onLoading(String imageUrl, long bytesRead, long totalBytes, boolean isDone,
                                   Exception exception);
}
