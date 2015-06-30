package com.light.myilists.model;

/**
 * Created by light on 15/6/30.
 */
public class VersionBean {

    private int versionCode;

    private String url;

    private int isforce;

    private String content;

    public VersionBean() {
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIsforce() {
        return isforce;
    }

    public void setIsforce(int isforce) {
        this.isforce = isforce;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
