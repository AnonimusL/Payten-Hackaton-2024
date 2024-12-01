package com.payten.hacka.auth_service.service;

import com.payten.hacka.auth_service.dto.BusinessDetailDto;
import com.payten.hacka.auth_service.dto.BusinessDto;

import java.util.List;

public interface BusinessService {
    List<BusinessDto> getAll();
}
