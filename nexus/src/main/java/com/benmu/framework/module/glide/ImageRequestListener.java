package com.benmu.framework.module.glide;

import com.bumptech.glide.request.RequestListener;

public abstract class ImageRequestListener implements RequestListener, OnProgressListener {

    public ImageRequestListener() {
        ProgressManager.addProgressListener(this);
    }

}
