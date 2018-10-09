package com.eros.framework.event;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.eros.framework.adapter.router.RouterTracker;
import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.model.BaseEventBean;
import com.eros.framework.model.WeexEventBean;
import com.eros.framework.utils.JsPoster;
import com.eros.wxbase.EventGate;
import com.eros.wxbase.EventGateFactory;
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
                reflectionClazzPerform("com.eros.framework.event.http.EventFetch", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;

            case WXEventCenter.EVENT_IMAGE_PICK:
            case WXEventCenter.EVENT_IMAGE_SCAN:
                reflectionClazzPerform("com.eros.framework.event.camera.EventImage", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;

            case WXEventCenter.EVENT_CAMERA_UPLOADIMAGE:
            case WXEventCenter.EVENT_CAMERA_PATH:
            case WXEventCenter.EVENT_CAMERA:
                reflectionClazzPerform("com.eros.framework.event.camera.EventCamera", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;


            case WXEventCenter.EVENT_RESIGNKEYBOARD:
            case WXEventCenter.EVENT_ISINSTALLWXAPP:
            case WXEventCenter.EVENT_GETCID:
            case WXEventCenter.EVENT_COPYSTRING:
                reflectionClazzPerform("com.eros.framework.event.tool.EventTool", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;


            case WXEventCenter.EVENT_SHARE:
            case WXEventCenter.EVENT_RELAYTOFRIEND:
            case WXEventCenter.EVENT_RELAYTOCRICLE:
                reflectionClazzPerform("com.eros.erospluginumeng.event.EventShare", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;


            case WXEventCenter.EVENT_TABBAR_SHOWBADGE:
            case WXEventCenter.EVENT_TABBAR_HIDBADGE:
            case WXEventCenter.EVENT_TABBAR_OPENPAGE:
            case WXEventCenter.EVENT_TABBAR_SETTABBAR:
            case WXEventCenter.EVENT_TABBAR_WATCHINDEX:
            case WXEventCenter.EVENT_TABBAR_CLEARTABBARINFO:
            case WXEventCenter.EVENT_TABBAR_CLEARWATCH:
                reflectionClazzPerform("com.eros.framework.event.TabbarEvent", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;

            case WXEventCenter.EVENT_UPDATE_BUNDLE:
            case WXEventCenter.EVENT_DOWNLOAD_BUNDLE:
                reflectionClazzPerform("com.eros.framework.event.UpdateJsBundleEvent", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;
            case WXEventCenter.EVENT_COMMUNICATION_SMS:
            case WXEventCenter.EVENT_COMMUNICATION_CONTACTS:
                reflectionClazzPerform("com.eros.framework.event.EventCommunication", context
                        , weexEventBean
                        , "", weexEventBean.getKey());
                break;

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


    private void reflectionClazzPerform(String clazzName, Context context, WeexEventBean
            weexEventBean, String errosMsg) {
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

    private void reflectionClazzPerform(String clazzName, Context context, WeexEventBean
            weexEventBean, String errosMsg, String type) {
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
