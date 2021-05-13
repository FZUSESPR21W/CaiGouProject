package com.example.caigou_alpha.dao;


import com.example.caigou_alpha.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderDao extends JpaRepository<UserOrder,Integer> {
    @Query(value = "select o from UserOrder o where o.user_id= :user_id")
    List<UserOrder> selectUserOrderByUserId(@Param("user_id")Integer user_id);
}
