package com.payten.hacka.auth_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users", indexes = {@Index(columnList = "email"), @Index(columnList = "email, password")})
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(unique = true)
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated
    private Role role;
    @ManyToMany(mappedBy = "managers")
    private List<Business> business;
    @ManyToMany(mappedBy = "managers")
    private List<Company> companies;

    public User(String email, String password, String firstName, String lastName, Role role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
