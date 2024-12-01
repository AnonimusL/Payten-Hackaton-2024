package com.payten.hacka.auth_service.config;

import com.payten.hacka.auth_service.domain.Address;
import com.payten.hacka.auth_service.domain.Business;
import com.payten.hacka.auth_service.domain.User;
import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.CreateBusinessDto;
import com.payten.hacka.auth_service.dto.UserDto;
import com.payten.hacka.auth_service.mapper.CategoryMapperResolver;
import com.payten.hacka.auth_service.mapper.CompanyMapperResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Autowired
    private CompanyMapperResolver companyMapperResolver;
    @Autowired
    private CategoryMapperResolver categoryMapperResolver;
        @Bean
        public ModelMapper modelMapper() {
            ModelMapper modelMapper = new ModelMapper();

            modelMapper.typeMap(CreateBusinessDto.class, Business.class)
                    .addMappings(mapper -> {
                        // Custom resolver for company mapping
                        mapper.using(companyMapperResolver)
                                .map(src -> src.getCompany(), Business::setCompany);
                        mapper.using(categoryMapperResolver)
                                .map(src -> src.getCategory(), Business::setCategory);
                        // Automatic mapping for other fields
                        mapper.map(CreateBusinessDto::getName, Business::setName);
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
