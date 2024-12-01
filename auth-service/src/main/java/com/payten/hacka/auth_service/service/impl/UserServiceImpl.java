package com.payten.hacka.auth_service.service.impl;

import com.payten.hacka.auth_service.domain.Address;
import com.payten.hacka.auth_service.domain.User;
import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.CreateUserDto;
import com.payten.hacka.auth_service.dto.UserDetailDto;
import com.payten.hacka.auth_service.dto.UserDto;
import com.payten.hacka.auth_service.exceptions.NotFoundException;
import com.payten.hacka.auth_service.repository.AddressRepository;
import com.payten.hacka.auth_service.repository.UserRepository;
import com.payten.hacka.auth_service.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(CreateUserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserInfo(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(String.format("user with email: %s not found", email)));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDetailDto updateAddress(UUID id, AddressDto addressDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("user with id: %s not found", id)));

        Address address = modelMapper.map(addressDto, Address.class);
        address = addressRepository.save(address);
        user.setAddress(address);

        user = userRepository.save(user);
        System.out.println(user.getAddress());
        return modelMapper.map(user, UserDetailDto.class);
    }
}
