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
public class Freelancer {
    @Id
    private Long freelancerID;
    private String moreDetail;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_freelancer_id")
    private List<FreelancerEducationDetails> freelancerEducationDetails = new ArrayList<>();
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_c_id")
    private List<FreelancerCeritficatesDetails> cerificates= new ArrayList<>();

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_c_id")
    private List<FreelancerCategoryDetails> categoryDetails= new ArrayList<>();
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_user_id")
    private List<Project> userID= new ArrayList<>();
    private String  startDate;
    private String endDate;
}

