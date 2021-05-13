package com.example.caigou_alpha.service;


import com.example.caigou_alpha.dao.AdminUserDao;
import com.example.caigou_alpha.entity.AdminUser;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service//变成Spring 容器中的一个bean
public class AdminUserService {
    @Resource//获得将其他部分资源引入
    private AdminUserDao adminuserDao;

    public AdminUser findById(Integer id){
        return adminuserDao.findById(id).orElse(null);
    }

    public AdminUser findByAccount(Integer account){
        return adminuserDao.findByAccount(account);
    }

    public AdminUser findByName(String name){
        return adminuserDao.findByName(name);
    }
}
