package com.payten.hacka.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDto {
    private String category;
    private String name;
    private CompanyDto company;

    private List<UserDto> managers;

    private List<AddressDto> addresses;
}
