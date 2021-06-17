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

//    @Query(value = "select f from Food  f where f.ingredient like %?1% order by (" +
//            "case when f.ingredient = ?1 then 1 " +
//            "when f.ingredient like ?1% then 2 " +
//            "when f.ingredient like %?1% then 3 " +
//            "when f.ingredient like %?1 then 4 " +
//            "else 5" +
//            " end " +
//            ")")
    @Query(value = "select f from Food  f where f.ingredient like %?1% order by (f.ingredient)")
    Page<Food> findFoodByIngredient(String name,Pageable pageable);
}
