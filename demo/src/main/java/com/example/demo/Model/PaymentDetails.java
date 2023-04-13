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
public class PaymentDetails {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long paymentID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User fk_userID;
    private String amount;
    private String projectID;
    private String clientSecret;

   // private String status;

}
