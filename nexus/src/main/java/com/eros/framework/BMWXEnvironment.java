package com.eros.framework;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.eros.framework.constant.Constant;
import com.eros.framework.manager.impl.FileManager;
import com.eros.framework.model.PlatformConfigBean;
import com.eros.framework.utils.SharePreferenceUtil;

import java.io.File;
import java.util.Map;

/**
 * Created by Carry on 2017/8/23.
 */

public class BMWXEnvironment {
    public static PlatformConfigBean mPlatformConfig;
    public static Context mApplicationContext;
    public static Map<String, String> mCustomer;

    public static String loadBmLocal(Context context, Uri uri) {
        StringBuffer path = new StringBuffer();
        path.append(uri.getHost()).append(uri.getPath());
        if (!TextUtils.isEmpty(uri.getQuery())) {
            path.append("?").append(uri.getQuery());
        }

        if (Constant.INTERCEPTOR_ACTIVE.equalsIgnoreCase(SharePreferenceUtil.getInterceptorActive
                (context))) {
            return FileManager.getPathBundleDir(context, "bundle/" + path.toString())
                    .getAbsolutePath();

        } else {
            return String.format("%s/dist/%s", BMWXEnvironment.mPlatformConfig.getUrl()
                    .getJsServer
                            (), path.toString());
        }
    }
}
