package com.payten.hacka.auth_service.service.impl;

import com.payten.hacka.auth_service.domain.Address;
import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.StorageAddressDto;
import com.payten.hacka.auth_service.repository.AddressRepository;
import com.payten.hacka.auth_service.service.StorageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Override
    public List<StorageAddressDto> findAllStorageAddresses() {
        List<Address> addresses = addressRepository.findAllByStorageTrue();
        return addresses.stream()
                .map(address -> modelMapper.map(address, StorageAddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto addNewStorageAddress(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        address.setStorage(true);
        address = addressRepository.save(address);
        return modelMapper.map(address, AddressDto.class);
    }
}
