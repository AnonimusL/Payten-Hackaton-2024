package com.payten.hacka.auth_service.service;

import com.payten.hacka.auth_service.dto.*;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    BusinessDetailDto addBusiness(CreateBusinessDto createBusinessDto);
    CompanyDetailDto getCompanyDetails(UUID id);
    CompanyDetailDto updateCompanyAddress(UUID id, AddressDto addressDto);

    List<CompanyDto> getAll();
}
