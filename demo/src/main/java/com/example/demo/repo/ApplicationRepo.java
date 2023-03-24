package com.example.demo.repo;

import com.example.demo.Model.ApplyForTheProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<ApplyForTheProject,Long> {
    List<ApplyForTheProject> findByFreelancerID(long freelancerID);
}
