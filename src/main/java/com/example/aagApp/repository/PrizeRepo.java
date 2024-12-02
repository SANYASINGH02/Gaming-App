package com.example.aagApp.repository;

import com.example.aagApp.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeRepo extends JpaRepository<Prize,Long> {

}
