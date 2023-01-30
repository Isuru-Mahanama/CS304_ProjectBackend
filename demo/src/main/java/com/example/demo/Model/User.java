package com.example.demo.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"email"})
},indexes = @Index(name = "idx_userID_email",columnList = "userID, email"))
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

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

    @OneToOne
    @PrimaryKeyJoinColumn
    private Client client;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Language language;

}
