package com.payten.hacka.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
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
}
