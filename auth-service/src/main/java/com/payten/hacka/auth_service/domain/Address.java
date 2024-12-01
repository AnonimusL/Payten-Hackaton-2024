package com.payten.hacka.auth_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address extends BaseEntity {
    private String country;
    private String city;
    private String postcode;
    private String street;
    private String number;
    private boolean storage;
    @ManyToMany(mappedBy = "addresses")
    private List<Business> businesses;
}
