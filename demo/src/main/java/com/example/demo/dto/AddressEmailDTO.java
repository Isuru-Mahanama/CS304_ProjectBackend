package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressEmailDTO {
    private Long userID;
    private String email;
    private Long userAddressID;
    private String streetAddress;
    private String province;
    private String city;
    private String country;
}
