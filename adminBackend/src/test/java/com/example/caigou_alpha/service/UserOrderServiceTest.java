package com.example.caigou_alpha.service;

import com.example.caigou_alpha.dao.CustomMenuDao;
import com.example.caigou_alpha.dao.MenuDao;
import com.example.caigou_alpha.dao.UserDao;
import com.example.caigou_alpha.dao.UserOrderDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@Service
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class UserOrderServiceTest {
    @Autowired
    UserOrderService userOrderService;
    @Resource
    private UserOrderDao userOrderDao;

    @Resource
    private MenuDao menuDao;
    @Resource
    private UserDao userDao;
    @Test
    void getUserOrderInfo() {

        System.out.println(userOrderService.getUserOrderInfo());
    }
}
