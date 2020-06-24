package com.cquptkiller.a_guarder.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.system.ErrnoException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cquptkiller.a_guarder.R;
import com.cquptkiller.a_guarder.adapter.PermissionDetailAdapter;
import com.cquptkiller.a_guarder.data.AppInfo;
import com.cquptkiller.a_guarder.utils.PermissionUtil;
import com.cquptkiller.a_guarder.utils.SignaturesMsg;
import com.cquptkiller.a_guarder.utils.UploadApkUtil;

/**
 * Created by xushuzhan on 2016/4/29.
 */
public class PermissonDetailActivity extends AppCompatActivity {
    public static final String TAG = "PermissonDetailActivity";
    public static final int RESULT_OK = 1;
    public static final int RESULT_NO = 0;

    public static final int MAIN_ACTIVITY = 100;
    public static final int MALWARE_ACTIVITY = 101;
    RecyclerView recyclerview;
    PermissionDetailAdapter adapter;
    ImageView iv_icon;
    TextView tv_app_name;
    TextView tv_package_name;
    TextView tv_permission_count;
    Button button;
    Toolbar toolbar;
    ImageView DeleteToolbar;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar_usual);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("详情");
        setResult(RESULT_NO);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 1);
        Log.d("889", "onCreate: " + position);
        initView();
        setRecyclerView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        try {
            int location1 = getIntent().getIntExtra("jump_location", MAIN_ACTIVITY);
            if (location1 == MAIN_ACTIVITY) {
                iv_icon = (ImageView) findViewById(R.id.iv_pda_icon);
                iv_icon.setImageDrawable(MainActivity.appList.get(position).getAppIcon());

                tv_app_name = (TextView) findViewById(R.id.tv_app_name_pda);
                tv_app_name.setText(MainActivity.appList.get(position).getAppName());

                tv_package_name = (TextView) findViewById(R.id.tv_package_name_pda);
                tv_package_name.setText(MainActivity.appList.get(position).getPackageName());

                tv_permission_count = (TextView) findViewById(R.id.permission_counts_pda);
                tv_permission_count.setText(String.valueOf(MainActivity.appList.get(position).getMyPermission().length) + "条权限");
                button = (Button) findViewById(R.id.bt_pda_uninstall);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uninstallAPK(MainActivity.appList.get(position).getPackageName());
                        MainActivity.appList.remove(position);
                        finish();
                    }
                });
            } else if (location1 == MALWARE_ACTIVITY) {
                iv_icon = (ImageView) findViewById(R.id.iv_pda_icon);
                iv_icon.setImageDrawable(MalwareActivity.malwareList.get(position).getAppIcon());

                tv_app_name = (TextView) findViewById(R.id.tv_app_name_pda);
                tv_app_name.setText(MalwareActivity.malwareList.get(position).getAppName());

                tv_package_name = (TextView) findViewById(R.id.tv_package_name_pda);
                tv_package_name.setText(MalwareActivity.malwareList.get(position).getPackageName());

                tv_permission_count = (TextView) findViewById(R.id.permission_counts_pda);
                tv_permission_count.setText(String.valueOf(MalwareActivity.malwareList.get(position).getMyPermission().length) + "条权限");
                button = (Button) findViewById(R.id.bt_pda_uninstall);
                button.setBackground(getDrawable(R.drawable.upload));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                        SharedPreferences pref = getSharedPreferences("a_guider_data",
                                MODE_PRIVATE);
                        String ip = pref.getString("ip", "");
                        Log.d("12345678", "mainactivity: " + ip);
                        if (!ip.equals("")) {
                            UploadApkUtil uploadApkUtil = new UploadApkUtil(PermissonDetailActivity.this,
                                    MalwareActivity.malwareList.get(position).getApkDir(), ip);
                        } else {
                            Toast.makeText(PermissonDetailActivity.this, "与服务器的链连接出现了问题请稍后再试！", Toast.LENGTH_SHORT).show();
                        }}catch (Exception e){
                            Toast.makeText(PermissonDetailActivity.this, "网络异常，请重试！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                DeleteToolbar = (ImageView) findViewById(R.id.delete_malware_toobar);
                DeleteToolbar.setImageDrawable(getDrawable(R.drawable.bt_pda_uninstall));
                DeleteToolbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uninstallAPK(MalwareActivity.malwareList.get(position).getPackageName());
                        MalwareActivity.malwareList.remove(position);
                        finish();
                    }
                });

            }
        } catch (Exception e) {
            Log.d(TAG, "initView: 出错了-->" + e.getMessage());
        }


    }

    private void setRecyclerView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview_permission_detali_activity);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        int location = getIntent().getIntExtra("jump_location", MAIN_ACTIVITY);
        if (location == MAIN_ACTIVITY) {
            adapter = new PermissionDetailAdapter(position, MainActivity.appList);
            Log.e("123456789", "setRecyclerView: " + location);
        } else if (location == MALWARE_ACTIVITY) {
            adapter = new PermissionDetailAdapter(position, MalwareActivity.malwareList);
            Log.e("123456789", "setRecyclerView: " + location);
        }
        recyclerview.setAdapter(adapter);
    }

    private void uninstallAPK(String PackgeName) {
        Uri packageURI = Uri.parse("package:" + PackgeName);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        startActivity(uninstallIntent);
    }
}
