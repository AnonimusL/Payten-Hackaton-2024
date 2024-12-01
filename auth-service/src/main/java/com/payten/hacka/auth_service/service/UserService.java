package com.payten.hacka.auth_service.service;

import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.CreateUserDto;
import com.payten.hacka.auth_service.dto.UserDetailDto;
import com.payten.hacka.auth_service.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto registerUser(CreateUserDto userDto);

    UserDto getUserInfo(String email);

    UserDetailDto updateAddress(UUID id, AddressDto addressDto);
}
