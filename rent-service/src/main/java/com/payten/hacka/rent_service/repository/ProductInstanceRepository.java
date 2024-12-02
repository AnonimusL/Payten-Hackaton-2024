package com.payten.hacka.rent_service.repository;

import com.payten.hacka.rent_service.domain.Product;
import com.payten.hacka.rent_service.domain.ProductInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductInstanceRepository extends JpaRepository<ProductInstance, UUID> {

    List<ProductInstance> findAllByProduct(Product product);
}
