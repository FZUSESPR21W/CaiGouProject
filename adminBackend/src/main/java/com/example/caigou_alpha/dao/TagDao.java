package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao extends JpaRepository<Tags,Integer> {
}
