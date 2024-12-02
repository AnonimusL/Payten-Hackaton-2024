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
    private String category;
    private UUID businessId;
    private UUID addressId;
    private int available;
    private int inUse;
    private int maxNumForRent;
    private boolean deleted;
    @ManyToMany
    @JoinTable(
            name = "product_rental_units",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="rental_unit_id")
    )
    private List<RentalUnit> rentalUnits;
    @OneToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<ProductCategory> productSupportedCategories;
}
