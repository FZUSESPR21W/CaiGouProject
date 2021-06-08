package com.example.caigouapp;

import android.app.Application;
import android.content.Context;

import com.example.push.helper.PushHelper;

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        mContext = getApplicationContext();

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