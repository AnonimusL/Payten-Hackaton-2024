package com.payten.hacka.auth_service.config;

import com.payten.hacka.auth_service.domain.Address;
import com.payten.hacka.auth_service.domain.Business;
import com.payten.hacka.auth_service.domain.User;
import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.BusinessDto;
import com.payten.hacka.auth_service.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {
        @Bean
        public ModelMapper modelMapper() {
            ModelMapper modelMapper = new ModelMapper();

            modelMapper.typeMap(Business.class, BusinessDto.class)
                    .addMappings(mapper -> {
                        mapper.map(src -> src.getCategory().getName(), BusinessDto::setCategory);
                        mapper.map(Business::getManagers, BusinessDto::setManagers);
                        mapper.map(Business::getAddresses, BusinessDto::setAddresses);
                    });

            modelMapper.typeMap(User.class, UserDto.class)
                    .addMappings(mapper -> {
                        mapper.map(User::getEmail, UserDto::setEmail);
                        mapper.map(User::getFirstName, UserDto::setFirstName);
                        mapper.map(User::getLastName, UserDto::setLastName);
                        mapper.map(User::getRole, UserDto::setRole);
                    });

            modelMapper.typeMap(Address.class, AddressDto.class);

            return modelMapper;
        }
}
