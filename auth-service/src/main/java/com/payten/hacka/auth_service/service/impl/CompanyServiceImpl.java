package com.payten.hacka.auth_service.service.impl;

import com.payten.hacka.auth_service.domain.Address;
import com.payten.hacka.auth_service.domain.Business;
import com.payten.hacka.auth_service.domain.Company;
import com.payten.hacka.auth_service.domain.User;
import com.payten.hacka.auth_service.dto.*;
import com.payten.hacka.auth_service.exceptions.NotFoundException;
import com.payten.hacka.auth_service.repository.AddressRepository;
import com.payten.hacka.auth_service.repository.BusinessRepository;
import com.payten.hacka.auth_service.repository.CompanyRepository;
import com.payten.hacka.auth_service.repository.UserRepository;
import com.payten.hacka.auth_service.service.CompanyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private BusinessRepository businessRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public BusinessDetailDto addBusiness(CreateBusinessDto createBusinessDto) {
        Business business = modelMapper.map(createBusinessDto, Business.class);
        if(business.getCompany() == null) throw new NotFoundException(String.format("company with id: %s not found", createBusinessDto.getCompany()));
        if(business.getCategory() == null) throw new NotFoundException(String.format("category with id: %s not found", createBusinessDto.getCategory()));

        List<Address> addresses = addressRepository.findAllById(createBusinessDto.getAddresses());
        if (addresses.size() != createBusinessDto.getAddresses().size()) {
            throw new IllegalArgumentException("Some addresses were not found for the given IDs");
        }
        business.setAddresses(addresses);

        if (createBusinessDto.getManagers() != null && !createBusinessDto.getManagers().isEmpty()) {
            List<User> managers = userRepository.findAllById(createBusinessDto.getManagers());
            if (managers.size() != createBusinessDto.getManagers().size()) {
                throw new IllegalArgumentException("Some managers were not found for the given IDs");
            }
            business.setManagers(managers);
        }

        business = businessRepository.save(business);
        return modelMapper.map(business, BusinessDetailDto.class);
    }

    @Override
    public CompanyDetailDto getCompanyDetails(UUID id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("company with id %s not found",id)));

        return modelMapper.map(company, CompanyDetailDto.class);
    }

    @Override
    public CompanyDetailDto updateCompanyAddress(UUID id, AddressDto addressDto) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("company with id %s not found",id)));

        Address address = modelMapper.map(addressDto, Address.class);
        address = addressRepository.save(address);

        company.setMainAddress(address);
        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyDetailDto.class);
    }

    @Override
    public List<CompanyDto> getAll() {
        return companyRepository.findAll().stream().map(company -> modelMapper.map(company, CompanyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto addCompany(CreateCompanyDto createCompanyDto) {
        Address address = modelMapper.map(createCompanyDto.getMainAddress(), Address.class);
        address = addressRepository.save(address);

        User manager = modelMapper.map(createCompanyDto.getManager(), User.class);
        manager = userRepository.save(manager);

        Company company = modelMapper.map(createCompanyDto, Company.class);
        company.setMainAddress(address);
        company.setManagers(List.of(manager));
        companyRepository.save(company);

        return modelMapper.map(company, CompanyDto.class);
    }
}
