package com.example.caigou_alpha.service;

import com.example.caigou_alpha.dao.FoodDao;
import com.example.caigou_alpha.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FoodService {
    @Resource
    private FoodDao foodDao;

    public Page<Food> getFoodPageByName(String name,Integer pageNum,Integer pageSize){
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        String s1 = "%" + name + "%";
        String s2 = name + "%";
        String s3 = "%" + name ;

        return foodDao.findFoodByIngredient(name,pageable);
    }

    public Page<Food> getFoodPageAll(Integer pageNum,Integer pageSize){
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return foodDao.findAll(pageable);
    }
}
