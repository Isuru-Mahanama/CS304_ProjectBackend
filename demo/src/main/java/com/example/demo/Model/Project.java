package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Project {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long projectID;
    private String projectTitle;
    private String smallDescription;
    private String moreDescription;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subcategory> subcategoryList;

    private Date startDate;
    private Date endDate;
    private String projectPrize;

    // Define the relationship between Project and Ecategory entities


  //  private List<ProjectRelatedCategories> subCategories;
  //  @Lob
   // private byte[] projectFile;




}
