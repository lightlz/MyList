package com.light.myilists.http;

import android.content.Context;

import com.light.myilists.utils.AppUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by light on 15/6/30.
 */
public class HttpRequestMap {


    /**
     * TODO(请求版本信息)
     * @param context
     * @return
     */
    public static Map<String,String> versionMap(Context context){

        Map<String,String> map = new HashMap<>();

        int versionCode = AppUtil.getVersionCode(context);

        map.put("version",String.valueOf(versionCode));

        return  map;

    }


}
