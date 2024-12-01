package com.payten.hacka.auth_service.repository;

import com.payten.hacka.auth_service.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findAllByStorageTrue();
}
