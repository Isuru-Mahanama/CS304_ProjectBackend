package com.example.demo.repo;

import com.example.demo.Model.LanguageLevelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageLevelDetailsRepo extends JpaRepository<LanguageLevelDetails, Long> {
}
