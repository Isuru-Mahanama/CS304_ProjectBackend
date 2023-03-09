package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrizeDTO {
  //  private Long prizeID;
    private String prizeType;
    private String projectType;
    private double minimumPrize;
    private double maximumPrize;
}
