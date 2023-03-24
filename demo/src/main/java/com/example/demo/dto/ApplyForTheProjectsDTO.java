package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyForTheProjectsDTO {
    private Long projectApplicationID;
    private String moreDescription;
    private String selectedSubCategory;
    private long hourlyRate;
    private String selectedCurrency;
    private String resumeName;
    private String resumePath;
    private String  projectIDD;
    private long freelancerID;
    private String projectTitle;
    private Long clientID;
    private LocalDate applicationDate;

}
