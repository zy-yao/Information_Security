//package com.cquptkiller.a_guarder.utils;
//
//import android.util.Log;
//import android.widget.Toast;
//
//import com.cquptkiller.a_guarder.data.PermNameList;
//import com.cquptkiller.a_guarder.data.PermissionUsedInfo;
//import com.cquptkiller.a_guarder.view.MainActivity;
//
//import java.util.ArrayList;
//
//import de.robv.android.xposed.IXposedHookLoadPackage;
//import de.robv.android.xposed.XC_MethodHook;
//import de.robv.android.xposed.callbacks.XC_LoadPackage;
//
//import static de.robv.android.xposed.XposedBridge.log;
//import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
//
///**
// * Created by yao on 2018/12/22.
// */
//public class Monitor implements IXposedHookLoadPackage {
//    public static ArrayList<String> Test = new ArrayList<>();
//    @Override
//    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
//        log("开始咯..");
//
//        if (lpparam.packageName.equals("com.android.telephony")) {
//
//
//            log("HOOK" + lpparam.packageName);
//            log("HOOK error~!");
//            return;
//        }
//
//
//        findAndHookMethod("com.android.server.pm.PackageManagerService", lpparam.classLoader, "checkUidPermission", String.class, int.class, new XC_MethodHook() {
//            protected void beforeHookedMethod(MethodHookParam param) {
//                String permName = (String) param.args[0];
//                Integer uid = (Integer) param.args[1];
//                if (uid.intValue() > 10000&&(PermNameList.equals(permName)==true)) {
//                    log("uid>>>>>>" + uid + "*****Permission>>>>>" + permName);
//
//                                        /**输出一段Log信息**/
//                    Log.i("Mytest", "  :监听到uid为"+uid+"的APP正在请求使用"+permName+"权限");
//                    Test.add("lalalala"+uid);
//                    Log.d("Monitor_Main", "beforeHookedMethod: "+uid);
////                    PermissionUsedInfo permissionUsedInfo = new PermissionUsedInfo();
////                    permissionUsedInfo.setUid(uid);
////                    if (MainActivity.PermissionUsedInfoMap.get(uid) == null) {
////                        if (permissionUsedInfo.getPermissionNum(permName) == 1) {
////                            permissionUsedInfo.setINTERNET_Permission_Times(permissionUsedInfo.getINTERNET_Permission_Times() + 1);
////                        } else if (permissionUsedInfo.getPermissionNum(permName) == 2) {
////                            permissionUsedInfo.setSEND_SMS_Permission_Times(permissionUsedInfo.getSEND_SMS_Permission_Times() + 1);
////                        } else if (permissionUsedInfo.getPermissionNum(permName) == 3) {
////                            permissionUsedInfo.setCALL_PHONE_Permission_Times(permissionUsedInfo.getCALL_PHONE_Permission_Times() + 1);
////                        } else if (permissionUsedInfo.getPermissionNum(permName) == 4) {
////                            permissionUsedInfo.setACCESS_LOCATION_Permission_Times(permissionUsedInfo.getACCESS_LOCATION_Permission_Times() + 1);
////                        } else if (permissionUsedInfo.getPermissionNum(permName) == 5) {
////                            permissionUsedInfo.setREAD_CONTACTS_Permission_Times(permissionUsedInfo.getREAD_CONTACTS_Permission_Times() + 1);
////                        }
////                        MainActivity.PermissionUsedInfoMap.put(uid, permissionUsedInfo);
////                    }else {
////                        if (permissionUsedInfo.getPermissionNum(permName) == 1) {
////                            permissionUsedInfo.setINTERNET_Permission_Times( MainActivity.PermissionUsedInfoMap.get(uid).getINTERNET_Permission_Times() + 1);
////                        } else if (permissionUsedInfo.getPermissionNum(permName) == 2) {
////                            permissionUsedInfo.setSEND_SMS_Permission_Times(MainActivity.PermissionUsedInfoMap.get(uid).getSEND_SMS_Permission_Times() + 1);
////                        } else if (permissionUsedInfo.getPermissionNum(permName) == 3) {
////                            permissionUsedInfo.setCALL_PHONE_Permission_Times(MainActivity.PermissionUsedInfoMap.get(uid).getCALL_PHONE_Permission_Times() + 1);
////                        } else if (permissionUsedInfo.getPermissionNum(permName) == 4) {
////                            permissionUsedInfo.setACCESS_LOCATION_Permission_Times(MainActivity.PermissionUsedInfoMap.get(uid).getACCESS_LOCATION_Permission_Times() + 1);
////                        } else if (permissionUsedInfo.getPermissionNum(permName) == 5) {
////                            permissionUsedInfo.setREAD_CONTACTS_Permission_Times(MainActivity.PermissionUsedInfoMap.get(uid).getREAD_CONTACTS_Permission_Times() + 1);
////                        }
////                        MainActivity.PermissionUsedInfoMap.put(uid, MainActivity.PermissionUsedInfoMap.get(uid));
////                    }
//
//
//
//                }
//            }
//        });
//    }
//}