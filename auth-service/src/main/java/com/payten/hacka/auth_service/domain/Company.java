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
    private String name;
    @ManyToOne
    private Address mainAddress;
    @OneToMany(mappedBy = "company")
    private List<Business> businesses;
    @ManyToMany
    @JoinTable(
            name = "company_manager",
            joinColumns = @JoinColumn(name="company_id"),
            inverseJoinColumns = @JoinColumn(name="manager_id")
    )
    private List<User> managers;
}
