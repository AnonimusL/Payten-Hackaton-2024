package com.payten.hacka.auth_service.service;

import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.BusinessDto;
import com.payten.hacka.auth_service.dto.CompanyDto;
import com.payten.hacka.auth_service.dto.CreateBusinessDto;

public interface CompanyService {
    BusinessDto addBusiness(CreateBusinessDto createBusinessDto);
    CompanyDto getCompanyDetails(String name);
    CompanyDto updateCompanyAddress(AddressDto addressDto);
}
