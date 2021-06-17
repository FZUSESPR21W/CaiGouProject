package com.example.caigou_alpha.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyPicConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/www/javaweb/zhpart/upload/");
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/upload/");

        registry.addResourceHandler("/upload/**").
                addResourceLocations("file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
                        + "upload"+ System.getProperty("file.separator"));
    }


}
