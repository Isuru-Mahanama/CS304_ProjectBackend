package com.example.demo.repo;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
UserRepo extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User save(User user);
    Optional<User> findByEmail(String email);
}
