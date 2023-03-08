package com.example.demo.Model;

import com.example.demo.dto.SubcategoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.swing.text.Position;
import java.util.List;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(ProjectRelatedCategoryPK.class)
@DynamicUpdate
public class Subcategory {
    @Id
    @JoinColumn(name = "project ID")
  //  @Column(name = "ProjectID")
    private Long project;
    @Id
    @Column(name = "Sub Category ID")
    private Long esubCategoryID;

    private String category;
    @Column(name = "Sub Category Name")
    private String esubCategoryName;

}
