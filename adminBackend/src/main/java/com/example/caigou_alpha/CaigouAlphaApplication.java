package com.example.caigou_alpha;

import com.example.caigou_alpha.dao.UserOrderDao;
import com.example.caigou_alpha.entity.UserOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootApplication
public class CaigouAlphaApplication {


    public static void main(String[] args) {
        SpringApplication.run(CaigouAlphaApplication.class, args);
    }

}
