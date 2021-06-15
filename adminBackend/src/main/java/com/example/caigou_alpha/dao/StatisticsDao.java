//package com.example.caigou_alpha.dao;
//
//import com.example.caigou_alpha.entity.Statistics;
//import com.example.caigou_alpha.entity.UserOrder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//@Repository
////@Bean(name = "StatisticsDao")
//public interface StatisticsDao{
//
//    @Query(value = "SELECT * FROM user_order WHERE DATE_SUB(CURDATE(), INTERVAL 10 DAY) <= DATE(createtime)",nativeQuery = true)
//    List<UserOrder>  findTenRecentOrder();
//
//
//    @Query(value = "select * from user_order where date_format(createtime,'%Y-%m-%d') = ?1",nativeQuery = true)
//    List<UserOrder> findOrderBytime(String date);
//}
