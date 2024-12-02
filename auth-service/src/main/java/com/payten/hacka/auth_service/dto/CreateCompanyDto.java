package com.payten.hacka.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCompanyDto {
    private String name;
    private String nameEn;
    private AddressDto mainAddress;
    private UserDto manager;

}
