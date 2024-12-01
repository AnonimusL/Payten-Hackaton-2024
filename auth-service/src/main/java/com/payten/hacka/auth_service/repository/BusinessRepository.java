package com.payten.hacka.auth_service.repository;

import com.payten.hacka.auth_service.domain.Business;
import com.payten.hacka.auth_service.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BusinessRepository extends JpaRepository<Business, UUID> {
    List<Business> findByCategory(Category category);
}
