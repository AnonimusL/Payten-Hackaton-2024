package com.payten.hacka.rent_service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    private UUID id;
    private String name;
    private String nameEn;
    private String category;
    private String categoryEn;
    private UUID businessId;
    private UUID addressId;
    @Lob
    private byte[] photo;
    private int amount;
    private int maxNumForRent;
    private boolean deleted;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentalUnit> rentalUnits;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategory> productSupportedCategories;

    @PrePersist
    public void ensureId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
