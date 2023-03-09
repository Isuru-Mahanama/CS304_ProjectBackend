package com.example.demo.repo;

import com.example.demo.Model.Prize;
import com.example.demo.Model.ProjectCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeRepo extends JpaRepository<Prize,Long> {
}
