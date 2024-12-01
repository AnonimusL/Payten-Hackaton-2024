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
public class CompanyDto {
    private String name;
    private List<UserDto> managers;
    private AddressDto address;
    private List<CompanyBusinessDto> businesses;
}
