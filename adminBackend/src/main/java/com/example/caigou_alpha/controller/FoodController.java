package com.example.caigou_alpha.controller;

import com.example.caigou_alpha.annotation.UserLoginToken;
import com.example.caigou_alpha.common.Result;
import com.example.caigou_alpha.dao.FoodDao;
import com.example.caigou_alpha.entity.Food;
import com.example.caigou_alpha.service.FoodService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://domain2.com",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@RestController//标识此接口中所有都是返回json数据
@RequestMapping("/food")//给访问链接加个前缀
public class FoodController {

    @Resource
    private FoodDao foodDao;
    @Resource
    private FoodService foodService;

    @UserLoginToken
    @GetMapping("/getAllFoodPageByName")
    public Result<Page<Food>> getAllFoodByName(@RequestParam(required = true)String name,
                                       @RequestParam(required = true)Integer pageNum,
                                       @RequestParam(required = true) Integer pageSize){

        return Result.success(foodService.getFoodPageByName(name,pageNum,pageSize));
    }

    @UserLoginToken
    @GetMapping("/getAllFoodPage")
    public Page<Food> getAllFoodPage(@RequestParam(required = true)Integer pageNum,
                                       @RequestParam(required = true) Integer pageSize){

        return foodService.getFoodPageAll(pageNum,pageSize);
    }
}
