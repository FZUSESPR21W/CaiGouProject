//package com.example.caigou_alpha.controller;
//
//
//import com.example.caigou_alpha.annotation.UserLoginToken;
//import com.example.caigou_alpha.entity.Statistics;
//import com.example.caigou_alpha.service.StatisticsService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//
//@SuppressWarnings("rawtypes")
//@CrossOrigin(origins = "http://domain2.com",
//        maxAge = 3600,
//        methods = {RequestMethod.GET, RequestMethod.POST})
//@RestController//标识此接口中所有都是返回json数据
//@RequestMapping("/statistics")//给访问链接加个前缀
//public class StatisticsController {
//
//    @Resource
//    private StatisticsService statisticsService;
//
//    @UserLoginToken
//    @GetMapping("/findRecent")
//    public Statistics findRecent(){
//        return  statisticsService.getTenRecent();
//    }
//}
