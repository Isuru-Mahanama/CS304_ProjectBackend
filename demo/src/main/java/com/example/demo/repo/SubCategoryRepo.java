package com.example.demo.repo;


import com.example.demo.Model.Project;
import com.example.demo.Model.ProjectRelatedCategoryPK;
import com.example.demo.Model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SubCategoryRepo extends JpaRepository<Subcategory, ProjectRelatedCategoryPK>{
}