package com.benmu.framework.event;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.BaseEventBean;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.framework.utils.JsPoster;
import com.benmu.wxbase.EventGate;
import com.benmu.wxbase.EventGateFactory;
import com.squareup.otto.Subscribe;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;

/**
 * Created by Carry on 2017/8/23.
 */

public class DispatchEventCenter {
    private static DispatchEventCenter mInstance = new DispatchEventCenter();

    private DispatchEventCenter() {
    }

    public static DispatchEventCenter getInstance() {
        return mInstance;
    }


    public void register() {
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
    }

    public void unregister() {
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
    }

    private Context safeContext(Context context) {
        if (!(context instanceof Activity)) {
            context = RouterTracker.peekActivity();
        }
        return context;
    }

    @Subscribe
    public void onBaseEvent(BaseEventBean eventBean) {
        reflectionClazzPerform(eventBean);
    }

    @Subscribe
    public void onWeexEvent(WeexEventBean weexEventBean) {
        if (weexEventBean == null) return;
        Context context = safeContext(weexEventBean.getContext());
        if (context == null) return;
        String params = weexEventBean.getJsParams();
        switch (weexEventBean.getKey()) {
            case WXEventCenter.EVENT_IMAGE_UPLOAD:
            case WXEventCenter.EVENT_FETCH:
                reflectionClazzPerform("com.benmu.framework.event.http.EventFetch", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;

            case WXEventCenter.EVENT_CAMERA_UPLOADIMAGE:
            case WXEventCenter.EVENT_CAMERA_PATH:
            case WXEventCenter.EVENT_CAMERA:
                reflectionClazzPerform("com.benmu.framework.event.camera.EventCamera", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;


            case WXEventCenter.EVENT_RESIGNKEYBOARD:
            case WXEventCenter.EVENT_ISINSTALLWXAPP:
            case WXEventCenter.EVENT_GETCID:
            case WXEventCenter.EVENT_COPYSTRING:
                reflectionClazzPerform("com.benmu.framework.event.tool.EventTool", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;


            case WXEventCenter.EVENT_SHARE:
            case WXEventCenter.EVENT_RELAYTOFRIEND:
            case WXEventCenter.EVENT_RELAYTOCRICLE:
                reflectionClazzPerform("com.benmu.erospluginumeng.event.EventShare", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;

            case WXEventCenter.EVENT_COMMUNICATION_SMS:
            case WXEventCenter.EVENT_COMMUNICATION_CONTACTS:
                reflectionClazzPerform("com.benmu.framework.event.EventCommunication", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;

            //ToMap 不实现了。
//            case WXEventCenter.EVENT_TOMAP:
//                new EventToMap().toMap(params, context, weexEventBean.getJscallback());
//                break;
            case WXEventCenter.EVENT_GEOLOCATION_GET:
                reflectionClazzPerform(WXEventCenter.EVENT_GEOLOCATION_GET
                        , context
                        , weexEventBean
                        , "");
                break;
            default:
                reflectionClazzPerform(weexEventBean.getKey()
                        , context
                        , weexEventBean
                        , "");

                break;
        }
    }


    private void reflectionClazzPerform(String clazzName, Context context, WeexEventBean weexEventBean, String errosMsg) {
        reflectionClazzPerform(weexEventBean.getKey()
                , context
                , weexEventBean
                , "", null);
    }

    private void reflectionClazzPerform(BaseEventBean eventBean) {
        if (eventBean == null) return;
        if (TextUtils.isEmpty(eventBean.clazzName)) return;
        if (eventBean.context == null) return;
        EventGate event = EventGateFactory.getEventGate(eventBean.clazzName);
        if (null != event) {
            event.perform(eventBean.context, eventBean);
        }
    }

    private void reflectionClazzPerform(String clazzName, Context context, WeexEventBean weexEventBean, String errosMsg, String type) {
        EventGate event = EventGateFactory.getEventGate(clazzName);
        String params = weexEventBean.getJsParams();
        if (null != event) {
            if (TextUtils.isEmpty(type)) {
                event.perform(context, weexEventBean);
            } else {
                event.perform(context, weexEventBean, type);
            }
        } else {
            if (TextUtils.isEmpty(params)) {
                JsPoster.postFailed(weexEventBean.getJscallback());
            } else {
                JsPoster.postFailed(errosMsg, weexEventBean.getJscallback());
            }

        }
    }

}
