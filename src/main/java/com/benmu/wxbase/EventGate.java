package com.benmu.wxbase;

import android.content.Context;

import com.benmu.framework.model.BaseEventBean;
import com.benmu.framework.model.WeexEventBean;
import com.taobao.weex.bridge.JSCallback;

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
