package com.payten.hacka.rent_service.dto;

import com.payten.hacka.rent_service.domain.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDetailDto {
    private UUID id;
    private String name;
    private String nameEn;
    private String category;
    private String categoryEn;
    private UUID businessId;
    private UUID addressId;
    private int productAmount;
    private int maxNumForRent;

    private List<RentalUnitDto> rentalUnits;
    private List<ProductCategoryDto> categories;
}
