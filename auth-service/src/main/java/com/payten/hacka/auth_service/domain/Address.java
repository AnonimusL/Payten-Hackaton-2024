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
public class Address extends BaseEntity{
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String postcode;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @ManyToMany(mappedBy = "addresses")
    private List<Business> businesses;
}
