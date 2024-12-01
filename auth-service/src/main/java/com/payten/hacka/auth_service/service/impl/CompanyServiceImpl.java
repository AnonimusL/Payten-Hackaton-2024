package com.payten.hacka.auth_service.service.impl;

import com.payten.hacka.auth_service.domain.Address;
import com.payten.hacka.auth_service.domain.Business;
import com.payten.hacka.auth_service.domain.User;
import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.BusinessDto;
import com.payten.hacka.auth_service.dto.CompanyDto;
import com.payten.hacka.auth_service.dto.CreateBusinessDto;
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
    public BusinessDto addBusiness(CreateBusinessDto createBusinessDto) {
        Business business = modelMapper.map(createBusinessDto, Business.class);
        List<Address> addresses = createBusinessDto.getAddresses().stream()
                .map(addressDto -> modelMapper.map(addressDto, Address.class))
                .collect(Collectors.toList());

        addresses = addressRepository.saveAll(addresses);
        business.setAddresses(addresses);

        if (createBusinessDto.getManagers() != null && !createBusinessDto.getManagers().isEmpty()) {
            List<User> managers = userRepository.findAllById(createBusinessDto.getManagers());
            if (managers.size() != createBusinessDto.getManagers().size()) {
                throw new IllegalArgumentException("Some managers were not found for the given IDs");
            }
            business.setManagers(managers);
        }

        business = businessRepository.save(business);
        return modelMapper.map(business, BusinessDto.class);
    }

    @Override
    public CompanyDto getCompanyDetails(UUID id) {
        return null;
    }

    @Override
    public CompanyDto updateCompanyAddress(UUID id, AddressDto addressDto) {
        return null;
    }
}
