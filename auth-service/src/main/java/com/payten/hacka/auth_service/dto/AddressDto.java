package com.payten.hacka.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
    private UUID id;
    private String country;
    private String countryEn;
    private String city;
    private String postcode;
    private String street;
    private String number;
    private Double longitude;
    private Double latitude;
}
