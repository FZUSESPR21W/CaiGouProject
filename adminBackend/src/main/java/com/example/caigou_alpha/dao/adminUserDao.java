package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserDao extends JpaRepository<AdminUser,Integer> {
    @Query(value = "select * from app_admin where app_admin.app_admin_num = ?1",nativeQuery = true)
    AdminUser findByAccount(Integer account);

    @Query(value = "select * from app_admin where app_admin.app_admin_name = ?1",nativeQuery = true)
    AdminUser findByName(String app_admin_name);
}
