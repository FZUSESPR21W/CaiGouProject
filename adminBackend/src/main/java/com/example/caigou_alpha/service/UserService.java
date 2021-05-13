package com.example.caigou_alpha.service;

import com.example.caigou_alpha.dao.UserDao;
import com.example.caigou_alpha.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public User findOrderUser(Integer userId){
        return userDao.findOrder(userId);
    }

    public User findUserById(String userId) {
        int i = Integer.parseInt(userId);

        return userDao.findById(i).orElse(null);
    }

}
