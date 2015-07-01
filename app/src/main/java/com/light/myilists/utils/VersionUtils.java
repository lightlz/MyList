package com.light.myilists.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.light.myilists.R;

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


    /**
     * TODO 弹出更新窗口
     * @param context
     * @param content
     */
    public static void displayUpdateDialog(final Context context,String content, final String url){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.dialog_update));
        builder.setMessage(content);
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                download(context,url);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    /**
     * TODO 下载
     * @param context
     * @param url
     */
    public static void download(Context context,String url){

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        context.startActivity(intent);
    }


}
