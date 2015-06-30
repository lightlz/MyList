package com.light.myilists.utils;

import android.content.Context;

/**
 * Created by light on 15/6/30.
 */
public class VersionUtils {

    /**
     * TODO(是否需要更新)
     * @param context
     * @param versionCodeServer
     * @return
     */
    public static boolean isUpdate(Context context,int versionCodeServer){
        boolean isUpdate = false;

        int indexVersion = AppUtil.getVersionCode(context);
        if (versionCodeServer > indexVersion){
            isUpdate = true;
        }
        return isUpdate;
    }



}
