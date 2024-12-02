package com.payten.hacka.rent_service.repository;

import com.payten.hacka.rent_service.domain.RentalUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentalUnitRepository extends JpaRepository<RentalUnit, UUID> {
}
