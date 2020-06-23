package com.cquptkiller.a_guarder.data;

import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yao on 2018/12/22.
 */
public class AppInfo  {
    private String appName="";
    private String packageName="";
    private String versionName="";
    private   int versionCode=0;
    private Drawable appIcon=null;
    private String[] permission=null;
    private String [] permissionLabel=null;
    private String apkDir=null;
    private Signature[] signatures;

    public Signature[] getSignatures() {
        return signatures;
    }

    public void setSignatures(Signature[] signatures) {
        this.signatures = signatures;
    }

    public String getApkDir() {
        return apkDir;
    }

    public void setApkDir(String apkDir) {
        this.apkDir = apkDir;
    }




    public String[] getPermission() {
        return permission;
    }

    public void setPermission(String[] permission) {
        this.permission = permission;
    }


    public String[] getPermissionLabel() {
        return permissionLabel;
    }

    public void setPermissionLabel(String[] permissionLabel) {
        this.permissionLabel = permissionLabel;
    }


    public String[] getMyPermission() {
        return permission;
    }

    public void setMyPermission(String[] permission) {
        this.permission = permission;
    }






    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public void print()
    {
        Log.v("app","Name:"+appName+" Package:"+packageName);
        Log.v("app","Name:"+appName+" versionName:"+versionName);
        Log.v("app","Name:"+appName+" versionCode:"+versionCode);

        if(permission!=null){
            Log.v("app","Name:"+appName+" permissionCount:"+permission.length);
        }

    }
}
