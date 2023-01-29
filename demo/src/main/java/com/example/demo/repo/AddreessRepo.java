package com.example.demo.repo;

import com.example.demo.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddreessRepo  extends JpaRepository<Address, Long> {
}
