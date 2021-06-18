package com.example.caigouapp;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

import com.example.push.helper.PushHelper;

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        mContext = getApplicationContext();
        LitePal.initialize(this);

        //预初始化
        PushHelper.preInit(this);
        //正式初始化
        initPushSDK();
    }
    //创建一个静态的方法，以便获取context对象
    public static Context getContext(){
        return mContext;
    }

    private void initPushSDK() {
        /*
         * 判断用户是否已同意隐私政策
         * 当同意时，直接进行初始化；
         * 当未同意时，待用户同意后，通过PushHelper.init(...)方法进行初始化。
         */
        if (PushHelper.isMainProcess(this)) {
            //建议在线程中执行初始化
            new Thread(() -> PushHelper.init(getApplicationContext())).start();
        }
    }
}
//package com.example.caigouapp;
//
//import android.app.Application;
//import android.content.Context;
//import android.os.StrictMode;
//
////import com.squareup.leakcanary.LeakCanary;
////import com.squareup.leakcanary.RefWatcher;
//
//public class MyApplication extends Application {
//    private static Context mContext;
////    private static RefWatcher mRefWatcher;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        //获取context
//        mContext = getApplicationContext();
//
////        mRefWatcher = LeakCanary.install(this);
//        //setupLeakCanary();
//    }
//
////    public static RefWatcher getRefWatcher() {
////        return mRefWatcher;
////
////    }
//
//    //创建一个静态的方法，以便获取context对象
//    public static Context getContext(){
//        return mContext;
//    }
//
//    protected void setupLeakCanary() {
//        // 启用严格模式
//        enabledStrictMode();
//        // 判断是否是 HeapAnalyzerService 所属进程
////        if (LeakCanary.isInAnalyzerProcess(this)) {
////            // This process is dedicated to LeakCanary for heap analysis.
////            // You should not init your app in this process.
////            return;
////        }
////        // 注册 LeakCanary
////        LeakCanary.install(this);
//    }
//
//    private static void enabledStrictMode() {
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
//                .detectAll() //
//                .penaltyLog() //
//                .penaltyDeath() //
//                .build());
//    }
//}