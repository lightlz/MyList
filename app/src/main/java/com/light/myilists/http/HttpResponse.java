package com.light.myilists.http;

import com.light.myilists.model.VersionBean;
import com.light.myilists.utils.GsonUtils;

/**
 * Created by light on 15/6/30.
 */
public class HttpResponse {


    /**
     * TODO 处理版本信息 response
     * @param versionRet
     * @return
     */
    public static VersionBean handlerVersionRet(String versionRet){

        VersionBean bean = null;

        bean = GsonUtils.parseGsonToClass(versionRet,VersionBean.class);

        return bean;
    }





}
