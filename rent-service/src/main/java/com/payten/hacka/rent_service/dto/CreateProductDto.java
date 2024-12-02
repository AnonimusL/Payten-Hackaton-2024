package com.payten.hacka.rent_service.dto;

import com.payten.hacka.rent_service.domain.ProductInstance;
import com.payten.hacka.rent_service.domain.RentalUnit;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    private String category;
    private UUID businessId;
    private UUID addressId;
    private int available;

    private List<RentalUnitDto> rentalUnits;
    private List<ProductInstance> productInstances;
}
