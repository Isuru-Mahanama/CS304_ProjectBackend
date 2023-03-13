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
    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FreelancerEducationDetails> freelancerEducationDetails = new ArrayList<>();
    @OneToMany(mappedBy = "freelancerID", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FreelancerCeritficatesDetails> freelancerCeritficatesDetails = new ArrayList<>();

}

