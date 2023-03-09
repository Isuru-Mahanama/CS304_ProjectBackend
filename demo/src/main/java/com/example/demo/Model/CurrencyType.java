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
public class CurrencyType {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long currencyType;
    private String currency;
}
