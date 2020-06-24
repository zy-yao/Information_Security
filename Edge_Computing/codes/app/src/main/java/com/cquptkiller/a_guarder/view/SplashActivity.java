package com.cquptkiller.a_guarder.view;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.cquptkiller.a_guarder.R;
import com.cquptkiller.a_guarder.utils.PermissionUtil;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                PermissionUtil permissionUtil =new PermissionUtil();
                try {
                    permissionUtil.addAppDatasToArray(getPackageManager());
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(123);
            }
        }).start();


    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==123){
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
