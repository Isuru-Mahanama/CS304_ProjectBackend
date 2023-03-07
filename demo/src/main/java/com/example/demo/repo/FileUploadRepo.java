package com.example.demo.repo;


import com.example.demo.Model.FileUplod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepo extends JpaRepository<FileUplod,Long> {

}
