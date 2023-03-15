package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FreelancerCategoryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String category;
    @Column(name = "Sub Category ID")
    private Long esubCategoryID;

    @Column(name = "Sub Category Name")
    private String esubCategoryName;
}
