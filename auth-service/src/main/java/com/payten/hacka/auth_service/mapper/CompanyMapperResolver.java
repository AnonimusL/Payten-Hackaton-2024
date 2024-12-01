package com.payten.hacka.auth_service.mapper;

import com.payten.hacka.auth_service.domain.Company;
import com.payten.hacka.auth_service.exceptions.NotFoundException;
import com.payten.hacka.auth_service.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CompanyMapperResolver implements org.modelmapper.Converter<UUID, Company> {

    private final CompanyRepository companyRepository;

    @Override
    public Company convert(org.modelmapper.spi.MappingContext<UUID, Company> context) {
        UUID company = context.getSource();
        return companyRepository.findById(company)
                .orElse(null);
    }
}
