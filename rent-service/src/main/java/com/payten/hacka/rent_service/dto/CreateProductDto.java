package com.payten.hacka.rent_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProductDto {
    private String name;
    private String nameEn;
    private String category;
    private String categoryEn;
    private UUID businessId;
    private UUID addressId;
    private int amount;

    private List<RentalUnitDto> rentalUnits;
    private List<ProductCategoryDto> productSupportedCategories;
}
