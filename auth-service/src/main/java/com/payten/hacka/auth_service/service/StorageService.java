package com.payten.hacka.auth_service.service;

import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.StorageAddressDto;

import java.util.List;

public interface StorageService {

    List<StorageAddressDto> findAllStorageAddresses();

    AddressDto addNewStorageAddress(AddressDto addressDto);
}
