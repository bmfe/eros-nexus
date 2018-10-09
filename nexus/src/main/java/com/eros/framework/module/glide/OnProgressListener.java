package com.eros.framework.module.glide;


/**
 * Created by sunfusheng on 2017/6/14.
 */
public interface OnProgressListener {

    void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone,
                    Exception exception);
}
