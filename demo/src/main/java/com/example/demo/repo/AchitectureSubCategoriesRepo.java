package com.example.demo.repo;

import com.example.demo.Model.ArchitectureSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchitectureSubCategoriesRepo extends JpaRepository<ArchitectureSubCategory,Long> {
}
