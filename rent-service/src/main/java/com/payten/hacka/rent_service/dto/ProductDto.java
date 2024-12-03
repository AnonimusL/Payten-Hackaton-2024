package com.payten.hacka.rent_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private UUID id;
    private String name;
    private String nameEn;
    private String encodedPhoto;
    private String category;
    private String categoryEn;
    private UUID businessId;
    private UUID addressId;

}
