package com.benmu.wxbase;

import java.util.HashMap;

/**
 * Created by liuyuanxiao on 18/3/20.
 */

public class PluginComponentsAndModules {

    public HashMap<String, String> getPluginCommponents() {
        HashMap<String, String> sComponents = new HashMap<>();
        sComponents.putAll(getPluginAmapCommponents());
        return sComponents;
    }

    public HashMap<String, String> getPluginModules() {
        HashMap<String, String> sModules = new HashMap<>();
        sModules.putAll(getPluginAmapModules());
        return sModules;
    }

    /**
     * amap Commponents
     *
     * @return
     */
    private HashMap<String, String> getPluginAmapCommponents() {
        HashMap<String, String> amapCommponents = new HashMap<>();
        amapCommponents.put("weex-amap", "com.plugamap.component.WXMapViewComponent");
        amapCommponents.put("weex-amap-marker", "com.plugamap.component.WXMapMarkerComponent");
        amapCommponents.put("weex-amap-info-window", "com.plugamap.component.WXMapInfoWindowComponent");
        amapCommponents.put("weex-amap-circle", "com.plugamap.component.WXMapCircleComponent");
        amapCommponents.put("weex-amap-polyline", "com.plugamap.component.WXMapPolyLineComponent");
        amapCommponents.put("weex-amap-polygon", "com.plugamap.component.WXMapPolygonComponent");
        return amapCommponents;
    }
    /**
     * amap moudle
     *
     * @return
     */
    private HashMap<String, String> getPluginAmapModules() {
        HashMap<String, String> amapMoudles = new HashMap<>();
        amapMoudles.put("amap", "com.plugamap.module.WXMapModule");
        return amapMoudles;
    }
}
