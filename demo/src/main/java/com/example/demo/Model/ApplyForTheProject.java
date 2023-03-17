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
public class ApplyForTheProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectApplicationID;
    private String moreDescription;
    private String selectedSubCategory;
    private long hourlyRate;
    private String selectedCurrency;
    private String resumeName;
    private String resumePath;
    private String  projectIDD;
    private long freelancerID;
}
