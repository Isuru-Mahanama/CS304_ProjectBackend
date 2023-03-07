package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {
    private Long projectID;
    private String email;
    private String projectTitle;
    private String smallDescription;
    private String moreDescription;
    private String category;
    @JsonProperty("Ecategory")
    private List<SubcategoryDTO> Ecategory;
    @JsonProperty("Acategory")
    private List<SubcategoryDTO> Acategory;
    @JsonProperty("Ccategory")
    private List<SubcategoryDTO> Ccategory;
    private Date startDate;
    private Date endDate;

    private String projectPrize;



}
