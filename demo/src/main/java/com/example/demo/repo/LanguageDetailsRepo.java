package com.example.demo.repo;

import com.example.demo.Model.Language;
import com.example.demo.Model.LanguageDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageDetailsRepo extends JpaRepository<LanguageDetails, Long> {
}
