package com.payten.hacka.rent_service.dto;

import com.payten.hacka.rent_service.domain.Product;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductInstanceDto {
    private UUID id;
    private String label;
    private int amount;
}
