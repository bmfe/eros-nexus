package com.eros.wxbase;

import android.content.Context;

import com.eros.framework.model.BaseEventBean;
import com.eros.framework.model.WeexEventBean;

/**
 *  事件抽象类
 * Created by liuyuanxiao on 17/12/4.
 */

public abstract class EventGate {

    public void perform(Context context,BaseEventBean eventBean){

    }

    public void perform(Context context,WeexEventBean weexEventBean){

    }

    public void perform(Context context,WeexEventBean weexEventBean,String type){

    }
}
