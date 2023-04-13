package com.example.demo.repo;

import com.example.demo.Model.PaymentDetails;
import com.example.demo.Model.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentDetails,Long> {
}
