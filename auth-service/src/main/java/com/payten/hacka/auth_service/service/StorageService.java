package com.payten.hacka.auth_service.service;

import com.payten.hacka.auth_service.dto.AddressDto;

import java.util.List;

public interface StorageService {

    List<AddressDto> findAllStorageAddresses();

    AddressDto addNewStorageAddress(AddressDto addressDto);
}
