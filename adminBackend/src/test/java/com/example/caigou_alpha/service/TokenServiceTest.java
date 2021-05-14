package com.example.caigou_alpha.service;

import com.example.caigou_alpha.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.soap.Addressing;

import static org.junit.jupiter.api.Assertions.*;
@Service
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class TokenServiceTest {
    @Autowired
    TokenService tokenService;
    @Test
    void getToken() {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(1);
        adminUser.setApp_admin_password("testtest");
        System.out.println(tokenService.getToken(adminUser));
    }
}
