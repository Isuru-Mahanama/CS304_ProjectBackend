package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class EngineeringSubcategories {
    @Id
    private Long ESubCategoryID;
    private String ESubCategoryName;

}
