package com.example.demo.dto;

import com.example.demo.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {

    private Long userID;
    private String email;
    private String firstName;
    private String lastName;
    private String displayEmail;
    private int phoneNumber;
    private String postalCode;
    private String userNames;

    private String company;

    private String location;
    private String timeZone;
    private String password;

    private Role role;


}
