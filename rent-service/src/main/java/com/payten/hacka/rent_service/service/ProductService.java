package com.payten.hacka.rent_service.service;

import com.payten.hacka.rent_service.dto.CreateProductDto;
import com.payten.hacka.rent_service.dto.ProductDetailDto;
import com.payten.hacka.rent_service.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDetailDto createProduct(CreateProductDto createProductDto);
    ProductDetailDto getProductInfo(UUID productId);
    List<ProductDto> findAll();

    List<ProductDto> findAllByLocation(UUID locationId);
}
