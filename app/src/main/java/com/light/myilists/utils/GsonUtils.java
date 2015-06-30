package com.light.myilists.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Created by light on 15/6/30.
 */
public class GsonUtils {

    /**
     * TODO : Gson to Class
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T parseGsonToClass(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return t;
    }

    /**
     * TODO : Gson to List
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> parseGsonToList(String jsonString, Class<T> cls) {
        List<T> list = null;
        Gson gson = new Gson();
        list = gson.fromJson(jsonString,new TypeToken<List<T>>() {
        }.getType());

        return list;
    }

    /**
     * TODO : Gson to List<Map>
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, T>> parseGsonToListMap(String jsonString) {
        List<Map<String, T>> list = null;
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    /**
     * TODO :  to Json
     * @param object
     * @return
     */
    public static String parseGsonString(Object object) {
        String gsonString = null;
        Gson gson = new Gson();
        gsonString = gson.toJson(object);
        return gsonString;
    }

    /**
     * TODO : Gson to Maps
     * @param gsonString
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> parseGsonToMaps(String gsonString) {
        Map<String, T> map = null;
        Gson gson = new Gson();
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        return map;
    }
}
