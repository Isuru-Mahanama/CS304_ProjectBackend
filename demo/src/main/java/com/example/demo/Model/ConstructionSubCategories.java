package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConstructionSubCategories {
    @Id
    private Long CSubCategoryID;
    private String CSubCategoryName;


}
