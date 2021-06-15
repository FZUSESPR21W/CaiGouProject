package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.MenuFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MenuFoodDao extends JpaRepository<MenuFood,Integer> {

    @Modifying
    @Transactional
    @Query("delete from MenuFood  menufood where menufood.menu_id = :id")
    void deleteSonRow(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `tmall`.`menu_food` (`menu_id`, `food_id_list`, `food_weight_list`) VALUES (?1, ?2, ?3)",nativeQuery = true)
    Integer addOneRecord(@Param("newId") Integer newId,@Param("foodList") String foodListString,@Param("weightList") String weightListString);

}
