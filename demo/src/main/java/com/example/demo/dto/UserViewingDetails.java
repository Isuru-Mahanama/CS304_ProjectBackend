package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserViewingDetails {
    private String userName;
    private String memeber;
    private String description;
    private String languages;
    private String education;
    private String certificates;
}
