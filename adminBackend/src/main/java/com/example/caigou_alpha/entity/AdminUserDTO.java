package com.example.caigou_alpha.entity;

import lombok.Data;

/**
 *与前端数据交互的类，主要用于登录与注册时自动关联映射Json数据
 */
@Data
public class AdminUserDTO {

    private  String name;
    private String password;
}
