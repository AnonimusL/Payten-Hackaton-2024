package com.payten.hacka.auth_service.service.impl;

import com.payten.hacka.auth_service.domain.Address;
import com.payten.hacka.auth_service.domain.Category;
import com.payten.hacka.auth_service.dto.BusinessDto;
import com.payten.hacka.auth_service.exceptions.NotFoundException;
import com.payten.hacka.auth_service.repository.AddressRepository;
import com.payten.hacka.auth_service.repository.BusinessRepository;
import com.payten.hacka.auth_service.repository.CategoryRepository;
import com.payten.hacka.auth_service.service.BusinessService;
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
public class BusinessServiceImpl implements BusinessService {

    private BusinessRepository businessRepository;
    private CategoryRepository categoryRepository;
    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Override
    public List<BusinessDto> getAll() {
        return businessRepository.findAll().stream()
                .map(business -> modelMapper.map(business, BusinessDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BusinessDto> getAllByCategory(String categoryName) {
        Category category = categoryRepository.findById(categoryName).orElseThrow(() -> new NotFoundException(String.format("category with name: %s not found", categoryName)));

        return businessRepository.findByCategory(category).stream()
                .map(business -> modelMapper.map(business, BusinessDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BusinessDto> getAllByAddress(UUID addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(()->new NotFoundException("address not found"));
        return businessRepository.findBusinessesByAddress(address).stream()
                .map(business -> modelMapper.map(business, BusinessDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BusinessDto> getAllBusinesses(String categoryName, UUID addressId) {
        if(categoryName == null && addressId == null) return getAll();
        if(categoryName == null) return getAllByAddress(addressId);
        if(addressId == null) return getAllByCategory(categoryName);

        Address address = addressRepository.findById(addressId).orElseThrow(()->new NotFoundException("address not found"));
        Category category = categoryRepository.findById(categoryName).orElseThrow(() -> new NotFoundException(String.format("category with name: %s not found", categoryName)));
        return businessRepository.findByCategoryAndAddress(category, address).stream()
                .map(business -> modelMapper.map(business, BusinessDto.class))
                .collect(Collectors.toList());
    }
}
