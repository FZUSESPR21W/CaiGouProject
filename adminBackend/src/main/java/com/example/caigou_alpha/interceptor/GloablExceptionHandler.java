package com.example.caigou_alpha.interceptor;

import com.example.caigou_alpha.common.Constant;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JSONObject handleException(Exception e){
        String errorMsg = e.getMessage();
        JSONObject jsonObject = new JSONObject();
        if (errorMsg == null || errorMsg.equals("")) {
            errorMsg = "服务器出错";
        }
        //用户未登录
        else if(errorMsg.equals(Constant.UNAUTHORIZED)){
            jsonObject.put("code", Constant.UNAUTHORIZED);
            jsonObject.put("msg", "用户未登录，请登录后重试");

            return jsonObject;
        }
        //token为空
        else if(errorMsg.equals(Constant.TOKEN_NOT_FOUND)){
            jsonObject.put("code", Constant.TOKEN_NOT_FOUND);
            jsonObject.put("msg", "无token，请重新登录");

            return jsonObject;
        }
        //用户未找到
        else if(errorMsg.equals(Constant.USER_NOTFOUND)){
            jsonObject.put("code", Constant.USER_NOTFOUND);
            jsonObject.put("msg", "用户不存在");

            return jsonObject;
        }
        return jsonObject;
    }
}
