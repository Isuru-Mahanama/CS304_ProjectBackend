package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"categoryID"})
},indexes = @Index(name = "idx_categoryID_categoryName",columnList = "categoryID, categoryName"))
public class ProjectCategories {
    @Id
    private Long categoryID;
    private String categoryName;
}
