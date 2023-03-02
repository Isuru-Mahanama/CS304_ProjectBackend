package com.example.demo.repo;

import com.example.demo.Model.EngineeringSubcategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineeringCategoriesRepo extends JpaRepository<EngineeringSubcategories,Long> {
}
