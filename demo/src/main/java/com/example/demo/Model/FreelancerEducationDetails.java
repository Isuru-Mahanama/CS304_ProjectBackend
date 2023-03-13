package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "freelancerEducationDetails")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FreelancerEducationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EducationID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freelancerID")
    private Freelancer freelancer;
    private String major;
    private String school;
    private String title;
    private String year;

}
