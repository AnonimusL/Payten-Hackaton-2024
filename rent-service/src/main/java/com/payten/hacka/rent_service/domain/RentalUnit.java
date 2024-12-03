package com.payten.hacka.rent_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    private String unitNameEn;
    @NotNull
    private BigDecimal pricePerUnit;
    @ManyToOne
    private Product product;

    @PrePersist
    public void ensureId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
