package com.example.caigou_alpha.service;

import com.example.caigou_alpha.entity.Menu;
import com.example.caigou_alpha.entity.UserOrder;
import org.hibernate.criterion.Order;
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
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Test
    void findPage() {

        UserOrder userOrder;

        int pageNum = 1;
        int pageSize = 5;
        userOrder = orderService.findPage(1,5).get().findFirst().orElse(null);
        System.out.println(userOrder);
    }
}
