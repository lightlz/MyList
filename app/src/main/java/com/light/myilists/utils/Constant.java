package com.light.myilists.utils;

import com.light.myilists.R;

/**
 * Created by light on 15/6/23.
 */
public class Constant {


    public static final int[] PRIORITY_COLOR = {
            R.color.cyan900,
            R.color.cyan800,
            R.color.cyan600,
            R.color.cyan400,
            R.color.cyan200};

    // 值为空 || 未定义
    public static final int VALUE_INDEFINE = -1;

    // Handler Message
    public static final int MSG_TODOLIST_CLICK = 0x100001;
    public static final int MSG_COMMON = 0;
    public static final int MSG_DISPLAY_TIP = 0x100002;

    // http url & type
    public static final String URL_VERSION = "http://1.mytdl.sinaapp.com/version.php";
    public static final int TYPE_VERSION = 0x200001;


}
