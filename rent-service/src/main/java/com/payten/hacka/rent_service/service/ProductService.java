package com.payten.hacka.rent_service.service;

import com.payten.hacka.rent_service.dto.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDetailDto createProduct(CreateProductDto createProductDto);
    ProductDetailDto getProductInfo(UUID productId);
    ProductsPerCategoryDto findAll(String categoryName);
    ProductsPerCategoryDto findAllByLocation(UUID locationId);
    List<ProductCategoryDto> getAvailableProducts(UUID productId, LocalDateTime startDate, LocalDateTime endDate);

}
