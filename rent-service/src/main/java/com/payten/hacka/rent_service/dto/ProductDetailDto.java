package com.payten.hacka.rent_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDetailDto {
    private UUID id;
    private String name;
    private String category;
    private UUID businessId;
    private UUID addressId;
    private int available;

    private List<RentalUnitDto> rentalUnits;
    private List<ProductInstanceDto> productInstances;
}
