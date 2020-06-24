package com.cquptkiller.a_guarder.data;

import java.security.PublicKey;

/**
 * Created by yao on 2018/12/22.
 * 动态监控模块的监测结果信息类
 */
public class PermissionUsedInfo {
    private int uid=0;
    private int INTERNET_Permission_Times=0;
    private int RECEIVE_SMS_Permission_Times=0;
    private int SEND_SMS_Permission_Times=0;
    private int CALL_PHONE_Permission_Times=0;
    private int ACCESS_LOCATION_Permission_Times=0;
    private int READ_CONTACTS_Permission_Times=0;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getINTERNET_Permission_Times() {
        return INTERNET_Permission_Times;
    }

    public void setINTERNET_Permission_Times(int INTERNET_Permission_Times) {
        this.INTERNET_Permission_Times = INTERNET_Permission_Times;
    }

    public int getRECEIVE_SMS_Permission_Times() {
        return RECEIVE_SMS_Permission_Times;
    }

    public void setRECEIVE_SMS_Permission_Times(int RECEIVE_SMS_Permission_Times) {
        this.RECEIVE_SMS_Permission_Times = RECEIVE_SMS_Permission_Times;
    }

    public int getSEND_SMS_Permission_Times() {
        return SEND_SMS_Permission_Times;
    }

    public void setSEND_SMS_Permission_Times(int SEND_SMS_Permission_Times) {
        this.SEND_SMS_Permission_Times = SEND_SMS_Permission_Times;
    }

    public int getCALL_PHONE_Permission_Times() {
        return CALL_PHONE_Permission_Times;
    }

    public void setCALL_PHONE_Permission_Times(int CALL_PHONE_Permission_Times) {
        this.CALL_PHONE_Permission_Times = CALL_PHONE_Permission_Times;
    }
    public int getACCESS_LOCATION_Permission_Times() {
        return ACCESS_LOCATION_Permission_Times;
    }

    public void setACCESS_LOCATION_Permission_Times(int ACCESS_LOCATION_Permission_Times) {
        this.ACCESS_LOCATION_Permission_Times = ACCESS_LOCATION_Permission_Times;
    }


    public int getREAD_CONTACTS_Permission_Times() {
        return READ_CONTACTS_Permission_Times;
    }

    public void setREAD_CONTACTS_Permission_Times(int READ_CONTACTS_Permission_Times) {
        this.READ_CONTACTS_Permission_Times = READ_CONTACTS_Permission_Times;
    }

    public int getPermissionNum(String PermissionName){
        if(PermissionName.equals("android.permission.INTERNET")){
            //完全访问网络
            return 1;
        }else if(PermissionName.equals("android.permission.SEND_SMS")){
            //发送短信
            return 2;
        }else if(PermissionName.equals("android.permission.CALL_PHONE")){
            //打电话
            return 3;
        }else if(PermissionName.equals("android.permission.ACCESS_FINE_LOCATION")||PermissionName.equals("android.permission.ACCESS_COARSE_LOCATION")){
            //获取位置信息（精确位置和大概位置）
            return 4;
        }else if(PermissionName.equals("android.permission.READ_CONTACTS")){
            //读取通讯录
            return 5;
        }else {
            return 0;
        }
    }
}
