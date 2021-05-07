package com.example.caigou_alpha.controller;

import com.example.caigou_alpha.common.Result;
import com.example.caigou_alpha.entity.User;
import com.example.caigou_alpha.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://domain2.com",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@RestController//标识此接口中所有都是返回json数据
@RequestMapping("/user")//给访问链接加个前缀

public class UserController {
    @Resource
    private UserService userService;

    /**
     * /user/findOrderUser/{userId}
     * 查询用户信息
     * @param userId
     * @return null or 用户信息
     */

    @GetMapping("/findOrderUser/{userId}")
    public Result<User>  findOrderUser(@PathVariable Integer userId){
        return Result.success(userService.findOrderUser(userId));
    }
}
