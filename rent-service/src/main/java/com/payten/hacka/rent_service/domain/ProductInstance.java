package com.payten.hacka.rent_service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInstance {
    @Id
    private UUID id;
    @ManyToOne
    private Product product;
    private String label;
    private int amount;
    @OneToMany
    @JoinTable(
            name = "product_instance_categories",
            joinColumns = @JoinColumn(name = "product_instance_id"),
            inverseJoinColumns = @JoinColumn(name = "product_category_id")
    )
    private List<ProductCategory> productCategories;
}
