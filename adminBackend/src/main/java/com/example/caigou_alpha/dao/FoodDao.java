package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.Food;
import com.example.caigou_alpha.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface FoodDao extends JpaRepository<Food,Integer> {

    @Query(value = "select f from Food f where f.ingredient like %:name%")
    Page<Food> findFoodByIngredient(@Param("name") String name,Pageable pageable);
}
