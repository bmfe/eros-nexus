package com.eros.framework.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eros.framework.manager.Manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Carry on 2017/8/7. json parse
 */

public class ParseManager extends Manager {
    public <T> T parseObject(String jsonString, Class<T> clazz) {
        try {
            return JSON.parseObject(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public JSONObject parseObject(String jsonString) {
        try {
            return JSON.parseObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object parse(String s) {
        try {
            return JSON.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    public Object parse(String s, Class clazz) {
        try {
            return JSON.parseObject(s, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    public HashMap<String, String> parseFetchParams(String data) {
        HashMap<String, String> result = new HashMap<String, String>();
        JSONObject jsonObj = JSONObject.parseObject(data);
        Set<String> keys = jsonObj.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String str = it.next();
            result.put(str, jsonObj.get(str).toString());
        }
        return result;
    }
}


