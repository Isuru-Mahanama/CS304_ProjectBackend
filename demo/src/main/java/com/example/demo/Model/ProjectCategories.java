package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProjectCategories {
    @Id
    private Long categoryID;
    private String categoryName;
}
