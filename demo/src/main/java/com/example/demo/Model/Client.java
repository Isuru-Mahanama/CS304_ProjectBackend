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
public class Client {
    @Id
    private Long clientID;
    private String companyDetails;
    private String websiteLink;
    private String faceBookLink;
    private String instagramLink;
   // @OneToMany(mappedBy = "projectID")
   // private List<Project> userID;

}
