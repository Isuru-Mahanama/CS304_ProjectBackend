package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"projectID"})
},indexes = @Index(name = "projectID",columnList = "projectID"))
@DynamicUpdate
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
    private String projectType;
    private String prizeminimum;
    private String prizemaximum;

    private String currencyType;
 //   private Long userID;
    @OneToOne
    @PrimaryKeyJoinColumn
    private FileUplod fileUplod;

  /*  @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "projectIDD")
    private List<ApplyForTheProject> projectIDD= new ArrayList<>();*/

}
