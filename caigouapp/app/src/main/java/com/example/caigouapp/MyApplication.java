package com.example.caigouapp;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        mContext = getApplicationContext();
        LitePal.initialize(this);
    }
    //创建一个静态的方法，以便获取context对象
    public static Context getContext(){
        return mContext;
    }
}