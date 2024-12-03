package com.payten.hacka.rent_service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategory {
    @Id
    private UUID id;
    @ManyToOne
    private Product product;
    private String name;
    private String nameEn;
    private String catValue;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "parent_category_id", nullable = true)
    private ProductCategory parentCategory;
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategory> dependentCategories;

    public ProductCategory(UUID id, Product product, String name, String catValue, int amount, ProductCategory parentCategory) {
        this.id = id;
        this.product = product;
        this.name = name;
        this.catValue = catValue;
        this.amount = amount;
        this.parentCategory = parentCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @PrePersist
    public void ensureId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
