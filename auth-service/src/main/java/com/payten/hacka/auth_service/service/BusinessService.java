package com.payten.hacka.auth_service.service;

import com.payten.hacka.auth_service.dto.BusinessDetailDto;
import com.payten.hacka.auth_service.dto.BusinessDto;

import java.util.List;
import java.util.UUID;

public interface BusinessService {
    List<BusinessDto> getAll();

    List<BusinessDto> getAllByCategory(String category);

    List<BusinessDto> getAllByAddress(UUID addressId);

    List<BusinessDto> getAllBusinesses(String categoryName, UUID addressId);
}
