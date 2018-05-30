package com.benmu.framework;

import android.content.Context;
import android.net.Uri;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.impl.FileManager;
import com.benmu.framework.model.PlatformConfigBean;
import com.benmu.framework.utils.SharePreferenceUtil;
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
        if (Constant.INTERCEPTOR_ACTIVE.equalsIgnoreCase(SharePreferenceUtil.getInterceptorActive
                (context))) {
            String path = uri.getHost() + File.separator +
                    uri.getPath();
            return FileManager.getPathBundleDir(context, "bundle/" + path)
                    .getAbsolutePath();

        } else {
            return String.format("%s/dist/%s%s", BMWXEnvironment.mPlatformConfig.getUrl()
                    .getJsServer
                            (), uri.getHost(), uri.getPath());
        }
    }
}
