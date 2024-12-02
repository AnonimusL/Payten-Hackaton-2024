package com.payten.hacka.rent_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalUnit {
    @Id
    private UUID id;
    @NotBlank
    private String unitName; // "hour", "day", "week", "custom-unit"
    @NotNull
    private BigDecimal pricePerUnit;
    @ManyToMany(mappedBy = "rentalUnits")
    private List<Product> products;
}
