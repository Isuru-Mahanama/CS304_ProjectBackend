package com.example.demo.dto;

import com.example.demo.Model.FreelancerCeritficatesDetails;
import com.example.demo.Model.FreelancerEducationDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreelancerDetailsDTO {
    private Long freelancerID;
    private String email;
    private String  moreDetail;
    private FreelancerCeritficatesDetails[] cerificates;
    private FreelancerEducationDetails[] education;
    @JsonProperty("Ecategory")
    private List<SubcategoryDTO> Ecategory;
    @JsonProperty("Acategory")
    private List<ArchitectureSubCategoriesDTO> Acategory;
    @JsonProperty("Ccategory")
    private List<ConstructionSubCategoriesDTO> Ccategory;
    private String  startDate;
    private String endDate;

}
