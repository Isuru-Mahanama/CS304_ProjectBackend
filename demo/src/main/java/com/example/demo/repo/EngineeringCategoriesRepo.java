package com.example.demo.repo;

import com.example.demo.Model.EngineeringSubcategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineeringCategoriesRepo extends JpaRepository<EngineeringSubcategories,Long> {
}
