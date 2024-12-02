package com.payten.hacka.auth_service.dto;

import com.payten.hacka.auth_service.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusinessDto {
    private Category category;
    private String name;
    private String nameEn;
    private List<AddressDto> addresses;
}
