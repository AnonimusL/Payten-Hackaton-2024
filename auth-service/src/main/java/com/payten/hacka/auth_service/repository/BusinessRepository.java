package com.payten.hacka.auth_service.repository;

import com.payten.hacka.auth_service.domain.Address;
import com.payten.hacka.auth_service.domain.Business;
import com.payten.hacka.auth_service.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface BusinessRepository extends JpaRepository<Business, UUID> {
    List<Business> findByCategory(Category category);

    @Query("SELECT b FROM Business b JOIN b.addresses a WHERE a = :address")
    List<Business> findBusinessesByAddress(@Param("address") Address address);

    @Query("SELECT b FROM Business b WHERE b.category = :category AND :address MEMBER OF b.addresses")
    List<Business> findByCategoryAndAddress(@Param("category") Category category, @Param("address") Address address);

}
