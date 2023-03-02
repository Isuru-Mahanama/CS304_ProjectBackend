package com.example.demo.repo;

import com.example.demo.Model.ConstructionSubCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionSubCategoriesRepo extends JpaRepository<ConstructionSubCategories,Long> {
}
