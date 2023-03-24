package com.example.demo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"email"})
},indexes = @Index(name = "idx_userID_email",columnList = "userID, email"))
@DynamicUpdate
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

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
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> token;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Client client;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Language language;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Freelancer freelancer;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", displayEmail='" + displayEmail + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", postalCode='" + postalCode + '\'' +
                ", userNames='" + userNames + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

}
