package com.cquptkiller.a_guarder.view;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;
import com.cquptkiller.a_guarder.R;
import com.cquptkiller.a_guarder.adapter.PermissionAdapter;
import com.cquptkiller.a_guarder.data.AppInfo;
import com.cquptkiller.a_guarder.data.PermissionUsedInfo;

import com.cquptkiller.a_guarder.utils.PermissionUtil;
import com.cquptkiller.a_guarder.utils.SPUtils;
import com.cquptkiller.a_guarder.utils.SignaturesMsg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int PERMISSION_ADAPTER=1;
    Context mContext;

    RecyclerView recyclerview;
    PermissionAdapter permissionAdapter;
    ProgressDialog progressDialog;
    public static ArrayList<AppInfo> appList= new ArrayList<>();//用来存储获取的应用信息数据
    public static HashMap<Integer,PermissionUsedInfo> PermissionUsedInfoMap = new HashMap<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        permissionAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // 保存成功
                    String installationId = AVInstallation.getCurrentInstallation().getInstallationId();
                    // 关联  installationId 到用户表等操作……

                } else {
                    // 保存失败，输出错误信息
                }
            }
        });
        // 设置默认打开的 Activity
        PushService.setDefaultPushCallback(this, MainActivity.class);



        for (int i=0;i<appList.size();i++){
            Log.d("mian12345", "onCreate: "+appList.get(i).getApkDir());
        }


        /**注册下载完成广播**/
//        registerReceiver(downloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("应用列表");
//        PermissionUtil permissionUtil =new PermissionUtil();
//        permissionUtil.addAppDatasToArray(getPackageManager());
        initView();



//        if (!checkXposed("de.robv.android.xposed.installer")) {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//            dialog.setTitle("请安装Xposed框架！");
//            dialog.setMessage("本程序需要Xposed框架的支持，请下载");
//            dialog.setCancelable(false);
//            dialog.setPositiveButton("下载", new DialogInterface.
//                    OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                   // downloadApk();
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse("http://m.coolapk.com/dl?qr=ODQwMQ"));
//                    startActivity(intent);
//                }
//            });
//            dialog.setNegativeButton("取消", new DialogInterface.
//                    OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(MainActivity.this, "您可能无法使用某些功能！", Toast.LENGTH_SHORT).show();
//                }
//            });
//            dialog.show();
//        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initView() {
        recyclerview= (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        permissionAdapter=new PermissionAdapter(appList);
        recyclerview.setAdapter(permissionAdapter);
        permissionAdapter.setRecyclerViewListener(new PermissionAdapter.RecyclerViewListener() {

            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this,PermissonDetailActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("jump_location",PermissonDetailActivity.MAIN_ACTIVITY);

//                Log.d("MD5", "Name: "+appList.get(position).getPackageName());
//                Log.d("MD5", "MD5: "+ SignaturesMsg.signatureMD5(appList.get(position).getSignatures()));
//                String name = appList.get(position).getPackageName();
//                String md5 = SignaturesMsg.signatureMD5(appList.get(position).getSignatures());
//                SPUtils.put(MainActivity.this,name,md5);
                startActivityForResult(intent,PERMISSION_ADAPTER);

            }
        });
    }

//    private boolean checkXposed(String packgeName){
//        int checkCode=0;
//        for(int i=0;i<appList.size();i++){
//            if(appList.get(i).getPackageName().equals(packgeName)){
//                checkCode=1;
//                Log.d("123456789", "checkXposed: 有Xposed");
//                Toast.makeText(MainActivity.this, "已经安装Xposed！", Toast.LENGTH_SHORT).show();
//            }
//        }
//        if(checkCode==1){
//            return true;
//        }else {
//            return false;
//        }
//    }

//    /**下载APK**/
//    private void downloadApk() {
//        String apkUrl = "http://dl.coolapkmarket.com/down/apk_file/2016/0127/de.robv.android.xposed.installer-3.0alpha4-37.apk?_upt=e6d897561464003442&md5=7ff821c9c916622206862170a4c0c197";
//        Uri uri = Uri.parse(apkUrl);
//        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
//        request.setAllowedNetworkTypes(request.NETWORK_MOBILE| request.NETWORK_WIFI);
//        //设置是否允许漫游
//        request.setAllowedOverRoaming(false);
//        //设置文件类型
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(apkUrl));
//        request.setMimeType(mimeString);
//        //在通知栏中显示
//        request.setNotificationVisibility(request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setTitle("Xposed正在下载...");
//        request.setVisibleInDownloadsUi(true);
//        //sdcard目录下的download文件夹
//        request.setDestinationInExternalPublicDir("/apkText", "Xposed.apk");
//        // 将下载请求放入队列
//        downloadManager.enqueue(request);
//    }
//
//    private BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            /**下载完成后安装APK**/
//            installApk();
//        }
//    };

//    private void installApk() {
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        String filePath = "/sdcard/apkText/Xposed.apk";
//        i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
//        startActivity(i);
//    }

//    private void uninstallAPK(String PackgeName){
//        Uri packageURI = Uri.parse("package:"+PackgeName);
//        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
//        startActivity(uninstallIntent);
//    }
//
















    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意，这个地方最重要，关于解释，自己google吧
            intent.addCategory(Intent.CATEGORY_HOME);
            this.startActivity(intent);
            Toast.makeText(MainActivity.this, "已经切换至后台运行", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //左上角的刷新按钮
        if (id == R.id.action_flush) {
                    progressDialog = new ProgressDialog
                            (MainActivity.this);
                    progressDialog.setMessage("正在更新软件列表...");
                    progressDialog.setCancelable(true);
                    progressDialog.show();

                appList.clear();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PermissionUtil permissionUtil =new PermissionUtil();
                    try {
                        permissionUtil.addAppDatasToArray(mContext.getPackageManager());
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    hanlder.sendEmptyMessage(0123);
                }
            }).start();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_risking) {
            MalwareActivity.malwareList.clear();
            Intent intent = new Intent(MainActivity.this,MalwareActivity.class);
            intent.putExtra("jump_location",PermissonDetailActivity.MALWARE_ACTIVITY);
            startActivity(intent);

        } else if (id == R.id.nav_dun) {
            Toast.makeText(MainActivity.this, "努力研发中..", Toast.LENGTH_SHORT).show();
            Log.d("onNavigationIt121", "onNavigationItemSelected: "+PermissionUtil.UidAndAppName.get(10143));
        } else if (id == R.id.nav_protect) {
            Toast.makeText(MainActivity.this, "暂未开通这个功能", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_setting) {
            Toast.makeText(MainActivity.this, "暂未开通这个功能", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_exit) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    Handler hanlder = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0123){
                progressDialog.dismiss();
                initView();
                Toast.makeText(MainActivity.this, "列表已更新", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
