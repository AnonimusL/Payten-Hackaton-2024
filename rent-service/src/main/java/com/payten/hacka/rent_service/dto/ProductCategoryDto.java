package com.payten.hacka.rent_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategoryDto {
    private UUID id;
    private String name;
    private String nameEn;
    private String catValue;
    private int productAmount;
    private int inUse;
    private int available;
    private int level;
    private List<ProductCategoryDto> dependentCategories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategoryDto that = (ProductCategoryDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
