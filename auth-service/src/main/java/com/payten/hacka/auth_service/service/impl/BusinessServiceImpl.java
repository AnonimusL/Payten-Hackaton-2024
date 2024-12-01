package com.payten.hacka.auth_service.service.impl;

import com.payten.hacka.auth_service.dto.BusinessDetailDto;
import com.payten.hacka.auth_service.dto.BusinessDto;
import com.payten.hacka.auth_service.repository.BusinessRepository;
import com.payten.hacka.auth_service.service.BusinessService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private BusinessRepository businessRepository;
    private ModelMapper modelMapper;
    @Override
    public List<BusinessDto> getAll() {
        return businessRepository.findAll().stream()
                .map(business -> modelMapper.map(business, BusinessDto.class))
                .collect(Collectors.toList());
    }
}
