package com.cquptkiller.a_guarder.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cquptkiller.a_guarder.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xushuzhan on 2016/8/9.
 */
public class MyReceiver extends BroadcastReceiver {
    public  static final String TAG = "MyReceiver";
    public static String message = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Get Broadcat");
        //if (intent.getAction().equals("com.xushuzhan.SET_IP")) {}
        try {

            //获取消息内容
            JSONObject json = new JSONObject(intent.getExtras().getString("com.avos.avoscloud.Data"));
            message = json.getString("alert");
            Log.d(TAG, "onReceive: "+message);
            if(message!=null){
                SPUtils.put(APP.getContext(),"ip",message);
            }


        } catch (JSONException e) {
            Log.d(TAG, "JSONException: " + e.getMessage());
        }
    }
}
