package com.example.caigou_alpha.dao;


import com.example.caigou_alpha.entity.Menu;
import com.example.caigou_alpha.entity.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserOrderDao extends JpaRepository<UserOrder,Integer> {
    @Query(value = "select o from UserOrder o where o.user_id= :user_id")
    List<UserOrder> selectUserOrderByUserId(@Param("user_id")Integer user_id);

    @Query(value = "select * from user_order where id = :id",nativeQuery = true)
    UserOrder selectOneOrder(@Param("id")Integer id);

    @Query(value = "SELECT uo FROM  UserOrder  uo where uo.status = :status ")
    Page<UserOrder> findAllStatus(@Param("status") Integer status,Pageable pageable);

    @Query(value = "SELECT uo FROM  UserOrder  uo where uo.status = :status and uo.id =:orderId")
    Page<UserOrder> findAllOrderId(@Param("status") Integer status, @Param("orderId") Integer orderId,Pageable pageable);

    @Query(value = "SELECT uo FROM  UserOrder  uo where  uo.id =:orderId")
    Page<UserOrder> findAllOrderIdAll(@Param("orderId") Integer orderId,Pageable pageable);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserOrder  uo set uo.status = 3 where uo.id =?1")
    int changeStatus(Integer orderId);
}
