package com.cquptkiller.a_guarder.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.Signature;
import android.util.Log;

import com.cquptkiller.a_guarder.data.AppInfo;
import com.cquptkiller.a_guarder.view.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yao on 2018/12/22.
 */
public class PermissionUtil {
    public static final String TAG = "PermissionUtil";
    public static HashMap<Integer,String> UidAndAppName=new HashMap<>();
    public  boolean addAppDatasToArray(PackageManager packageManager) throws PackageManager.NameNotFoundException {

        List<PackageInfo> packages = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);//用来获取包的信息
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            Log.d(TAG, "addAppDatasToArray: "+packageInfo.applicationInfo.uid+"->"+packageInfo.applicationInfo.loadLabel(packageManager).toString());

            //保存相关数据信息
            AppInfo tmpInfo = new AppInfo();

            //判断一个应用是否为系统应用
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                //为第三方应用
                tmpInfo.setAppName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                tmpInfo.setPackageName(packageInfo.packageName);
                tmpInfo.setVersionName(packageInfo.versionName);
                tmpInfo.setVersionCode(packageInfo.versionCode);
                tmpInfo.setApkDir(packageInfo.applicationInfo.sourceDir);
                PackageInfo packageInfo2 = packageManager.getPackageInfo(packageInfo.packageName,PackageManager.GET_SIGNATURES);
                tmpInfo.setSignatures(packageInfo2.signatures);

                Log.d("sourceDir", "addAppDatasToArray: "+packageInfo.applicationInfo.sourceDir);
                //Uid和AppName的hashmap
                UidAndAppName.put(packageInfo.applicationInfo.uid,packageInfo.applicationInfo.loadLabel(packageManager).toString());
                // /权限集合
                tmpInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(packageManager));
                if (packageInfo.requestedPermissions != null) {
                    Log.d("addAppDatasToArray", "---->: " + packageInfo.packageName);
                    Log.d("addAppDatasToArray", ">>>>>>>>>>>: " + packageInfo.applicationInfo.loadLabel(packageManager).toString());
                    tmpInfo.setMyPermission(packageInfo.requestedPermissions);

                        String permissionLabel[] = new String[packageInfo.requestedPermissions.length];
                        for (int j = 0; j < packageInfo.requestedPermissions.length; j++) {
                            permissionLabel[j]=getPermissionLabel(packageInfo.requestedPermissions[j],packageManager);
                            Log.d("addAppDatasToArray", "addAppDatasToArray: >>>>>"+j+">>>>>"+tmpInfo.getMyPermission()[j]);
                            Log.d("addAppDatasToArray", "addAppDatasToArray: >>>>>"+j+">>>>>"+getPermissionLabel(packageInfo.requestedPermissions[j],packageManager));
                        }
                        tmpInfo.setPermissionLabel(permissionLabel);


                } else {
                    System.out.println("没有权限");
                }

                MainActivity.appList.add(tmpInfo);


            } else {
                //为系统应用

                System.out.println("系统应用");
            }
            tmpInfo.print();
        }
        return true;
    }
    public String getPermissionLabel(String permissionName,PackageManager pm){
        String strPermissionInfo;
        try {
            PermissionInfo permissionInfo = pm.getPermissionInfo(permissionName, 0);

            strPermissionInfo =  permissionInfo.loadLabel(pm).toString() ;
        } catch (PackageManager.NameNotFoundException e) {

            return "其他权限";
        }
        return strPermissionInfo;

    }
    public int getPermissionCount(String AppName){
        for(int i=0;i<MainActivity.appList.size();i++){
            if(MainActivity.appList.get(i).getAppName().equals(AppName)){
                return MainActivity.appList.get(i).getPermission().length;
            }
        }
        return 0;
    }
    public int isInternet(String AppName){
        for(int i=0;i<MainActivity.appList.size();i++){
            if(MainActivity.appList.get(i).getAppName().equals(AppName)){
                for(int j=0;j< MainActivity.appList.get(i).getMyPermission().length;j++){
                    if(MainActivity.appList.get(i).getMyPermission()[j].equals("android.permission.INTERNET")){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    public int isSendMSM(String AppName){
        for(int i=0;i<MainActivity.appList.size();i++){
            if(MainActivity.appList.get(i).getAppName().equals(AppName)){
                for(int j=0;j< MainActivity.appList.get(i).getMyPermission().length;j++){
                    if(MainActivity.appList.get(i).getMyPermission()[j].equals("android.permission.SEND_SMS")){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    public int isPhoneCall(String AppName){
        for(int i=0;i<MainActivity.appList.size();i++){
            if(MainActivity.appList.get(i).getAppName().equals(AppName)){
                for(int j=0;j< MainActivity.appList.get(i).getMyPermission().length;j++){
                    if(MainActivity.appList.get(i).getMyPermission()[j].equals("android.permission.CALL_PHONE")){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    public int isAceessLocal(String AppName){
        for(int i=0;i<MainActivity.appList.size();i++){
            if(MainActivity.appList.get(i).getAppName().equals(AppName)){
                for(int j=0;j< MainActivity.appList.get(i).getMyPermission().length;j++){
                    if(MainActivity.appList.get(i).getMyPermission()[j].equals("android.permission.ACCESS_FINE_LOCATION")||
                            MainActivity.appList.get(i).getMyPermission()[j].equals("android.permission.ACCESS_COARSE_LOCATION")){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    public int isReadContacts(String AppName){
        for(int i=0;i<MainActivity.appList.size();i++){
            if(MainActivity.appList.get(i).getAppName().equals(AppName)){
                for(int j=0;j< MainActivity.appList.get(i).getMyPermission().length;j++){
                    if(MainActivity.appList.get(i).getMyPermission()[j].equals("android.permission.READ_CONTACTS")){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

}
