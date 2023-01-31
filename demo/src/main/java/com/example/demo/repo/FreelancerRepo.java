package com.example.demo.repo;

import com.example.demo.Model.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerRepo extends JpaRepository<Freelancer,Long> {
}
