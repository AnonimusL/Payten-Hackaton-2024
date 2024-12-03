package com.payten.hacka.rent_service.mapper.config;

import com.payten.hacka.rent_service.domain.Product;
import com.payten.hacka.rent_service.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MapperConfig {

        @Bean
        public ModelMapper modelMapper() {
            ModelMapper modelMapper = new ModelMapper();

            return modelMapper;
        }
}
