package com.eros.framework.http;

import com.eros.framework.BMWXEnvironment;

/**
 * Created by Carry on 2017/8/21.
 */

public class Api {
    public static String BASE_URL = BMWXEnvironment.mPlatformConfig.getUrl().getRequest();
    public static String UPLOAD_URL = BMWXEnvironment.mPlatformConfig.getUrl().getImage();
}
