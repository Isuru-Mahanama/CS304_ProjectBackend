package com.example.demo.repo;

import com.example.demo.Model.ApplyForTheProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepo extends JpaRepository<ApplyForTheProject,Long> {
}
