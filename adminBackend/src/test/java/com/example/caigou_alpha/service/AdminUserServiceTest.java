package com.example.caigou_alpha.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@Service
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class AdminUserServiceTest {
    @Autowired
    AdminUserService adminUserService;
    @Test
    void findById() {
        int id = 1;
        System.out.println(adminUserService.findById(id));
    }

    @Test
    void findByAccount() {
        int account = 123;
        System.out.println(adminUserService.findByAccount(account));
    }

    @Test
    void findByName() {
        String name = "test1";
        System.out.println(adminUserService.findByName(name));
    }
}
