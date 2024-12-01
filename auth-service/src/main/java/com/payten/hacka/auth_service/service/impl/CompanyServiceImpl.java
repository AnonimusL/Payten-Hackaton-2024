package com.payten.hacka.auth_service.service.impl;

import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.BusinessDto;
import com.payten.hacka.auth_service.dto.CompanyDto;
import com.payten.hacka.auth_service.dto.CreateBusinessDto;
import com.payten.hacka.auth_service.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Override
    public BusinessDto addBusiness(CreateBusinessDto createBusinessDto) {
        return null;
    }

    @Override
    public CompanyDto getCompanyDetails(String name) {
        return null;
    }

    @Override
    public CompanyDto updateCompanyAddress(AddressDto addressDto) {
        return null;
    }
}
