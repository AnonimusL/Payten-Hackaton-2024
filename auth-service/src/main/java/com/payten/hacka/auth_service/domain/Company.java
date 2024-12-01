package com.payten.hacka.auth_service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company extends BaseEntity{
    @Column(unique = true)
    private String name;
    @ManyToOne
    private Address mainAddress;
    @OneToMany(mappedBy = "company")
    private List<Business> businesses;
    @OneToMany
    private List<User> managers;
}
