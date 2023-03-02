package com.example.demo.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstructionSubCategoriesDTO {

    private Long CSubCategoryID;
    private String CSubCategoryName;
}
