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
public class Prize {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long prizeID;
    private String prizeType;
    private String projectType;
    private double minimumPrize;
    private double maximumPrize;
}
