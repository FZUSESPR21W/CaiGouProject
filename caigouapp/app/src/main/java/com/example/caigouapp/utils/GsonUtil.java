package com.example.caigouapp.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.caigouapp.ui.order.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GsonUtil {
    //规定每段显示的长度
    private static int LOG_MAXLENGTH = 2000;
    public static List<Order> ParseOrderGson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("info");
            String data = jsonArray.toString();
            return new Gson().fromJson(data, new TypeToken<List<Order>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //模拟生成一个json字符串
    public static String getOrderJson(Context context){
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("json.txt")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static List<Order> getDataList(Context context){
        String data = getOrderJson(context);
        return ParseOrderGson(data);
    }

    public static Order getOrder(String json){
        return new Gson().fromJson(json, Order.class);
    }

    public static void e(String TAG, String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                Log.d(TAG + i, msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                Log.d(TAG, msg.substring(start, strLength));
                break;
            }
        }
    }
}
