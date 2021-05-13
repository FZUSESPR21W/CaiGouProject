package com.example.caigou_alpha.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.caigou_alpha.entity.AdminUser;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String getToken(AdminUser adminUser) {
        String token="";

        token= JWT.create().withAudience(String.valueOf(adminUser.getId()))
                .sign(Algorithm.HMAC256(adminUser.getApp_admin_password()));
        return token;
    }
}
