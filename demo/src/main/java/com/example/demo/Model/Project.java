package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Project {
    @Id
    private Long projectID;
    private String projectTitle;
    private String smallDescription;
    private String moreDescription;
    private Date startDate;
    private Date endDate;
    private String projectPrize;

    @Lob
    private byte[] projectFile;




}
