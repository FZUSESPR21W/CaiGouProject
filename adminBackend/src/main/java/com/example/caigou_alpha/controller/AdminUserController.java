package com.example.caigou_alpha.controller;


import com.example.caigou_alpha.annotation.UserLoginToken;
import com.example.caigou_alpha.common.Constant;
import com.example.caigou_alpha.common.Result;
import com.example.caigou_alpha.entity.AdminUser;
import com.example.caigou_alpha.entity.AdminUserDTO;
import com.example.caigou_alpha.entity.User;
import com.example.caigou_alpha.service.AdminUserService;
import com.example.caigou_alpha.service.TokenService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://domain2.com",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@RestController//标识此接口中所有都是返回json数据
@RequestMapping("/adminUser")//给访问链接加个前缀
public class AdminUserController {
    @Resource
    private AdminUserService adminUserService;
    @Resource
    private TokenService tokenService;

    /**
     * /adminUser/getById/{id}
     * 查询是否存在这个账号的用户
     * @param id
     * @return 若存在返回该用户，不存在返回NULL
     *
     */
    @UserLoginToken
    @GetMapping("/getById/{id}")
    public Result<AdminUser> findById(@PathVariable Integer id){
        return Result.success(adminUserService.findById(id));
    }

    /**
     * /adminUser/getByAccount/{account}
     * 根据账号查询管理员用户，验证登录
     * @param account
     * @return null or 管理员信息
     */
    @UserLoginToken
    @GetMapping("/getByAccount/{account}")
    public Result<AdminUser> findByAccount(@PathVariable Integer account){
        return Result.success(adminUserService.findByAccount(account));
    }

    /**
     * /adminUser/login
     * name,password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody AdminUserDTO adminuserDTO)  {
        JSONObject jsonObject=new JSONObject();
        AdminUser adminUserForBase=adminUserService.findByName(adminuserDTO.getName());
        if(adminUserForBase==null){
            jsonObject.put("code", Constant.USER_NOTFOUND);
            jsonObject.put("msg","登录失败,用户不存在");
            jsonObject.put("content",adminuserDTO.getName());
            return jsonObject;
        }else {
            if (!adminUserForBase.getApp_admin_password().equals(adminuserDTO.getPassword())){
                jsonObject.put("code", Constant.INVALID_PASSWORD);
                jsonObject.put("msg","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(adminUserForBase);
                jsonObject.put("token", token);
                jsonObject.put("adminUser", adminUserForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
