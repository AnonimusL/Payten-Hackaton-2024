package com.payten.hacka.rent_service.repository;

import com.payten.hacka.rent_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByAddressId(UUID addressId);

    List<Product> findByCategory(String category);
}
