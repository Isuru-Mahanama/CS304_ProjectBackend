package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FreelancerCeritficatesDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FreelancerCertificatesDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freelancerID")
    private Freelancer freelancerID;
    private String certificate;
    private String cerifiedfrom;
    private String year;

}
