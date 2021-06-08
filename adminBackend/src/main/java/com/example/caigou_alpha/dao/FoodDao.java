package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface FoodDao extends JpaRepository<Food,Integer> {
}
