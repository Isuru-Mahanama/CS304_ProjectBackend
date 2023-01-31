package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long clientID;
    private String email;
    private String companyDetails;
    private String websiteLink;
    private String faceBookLink;
    private String instagramLink;

}
