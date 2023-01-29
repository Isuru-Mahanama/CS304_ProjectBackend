package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long userID;
    private String email;
    private String firstName;
    private String lastName;
    private String displayEmail;
    private int phoneNumber;
    private String postalCode;
    private String userName;

    private String company;

    private String location;


}
