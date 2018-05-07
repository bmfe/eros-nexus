package com.benmu.framework.model;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by liuyuanxiao on 18/4/9.
 */

public class BaseEventBean implements Serializable {
    public Context context;
    public String param;
    public String type;
    public String clazzName;

}
