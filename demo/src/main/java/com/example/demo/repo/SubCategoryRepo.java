package com.example.demo.repo;


import com.example.demo.Model.ProjectRelatedCategoryPK;
import com.example.demo.Model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<Subcategory, ProjectRelatedCategoryPK>{
  List<Subcategory> findByProject(long parseLong);
}