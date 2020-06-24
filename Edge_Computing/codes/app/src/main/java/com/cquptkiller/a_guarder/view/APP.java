package com.cquptkiller.a_guarder.view;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by xushuzhan on 2016/8/9.
 */
public class APP extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        AVOSCloud.initialize(this,"Ayu2wQxSMlmQNCcW7Aqnaxds-gzGzoHsz","lPltbvex6p91DaP2JgqR5DzU");
    }
    public static Context getContext() {
        return context;
    }
}
