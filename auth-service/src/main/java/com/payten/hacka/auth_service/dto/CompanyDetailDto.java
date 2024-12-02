package com.payten.hacka.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyDetailDto {
    private String name;
    private String nameEn;
    private List<UserDto> managers;
    private AddressDto address;
    private List<CompanyBusinessDto> businesses;
}
