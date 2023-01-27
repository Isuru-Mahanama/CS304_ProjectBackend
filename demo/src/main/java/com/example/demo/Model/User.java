package com.example.demo.Model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Indexed;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long userID;
    private String email;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String houseNo;
    private String street;
    private String city;
    private String userName;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Client client;

}
