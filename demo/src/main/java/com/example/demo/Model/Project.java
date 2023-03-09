package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Project {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long projectID;
    private String email;
    private String projectTitle;
    private String smallDescription;
    private String moreDescription;


    private Date startDate;
    private Date endDate;
    private String prize;
    private String prizeminimum;
    private String prizemaximum;

    private String projectPrize;
    @OneToOne
    @PrimaryKeyJoinColumn
    private FileUplod fileUplod;



}
