package com.payten.hacka.auth_service.mapper;

import com.payten.hacka.auth_service.domain.Category;
import com.payten.hacka.auth_service.exceptions.NotFoundException;
import com.payten.hacka.auth_service.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryMapperResolver implements org.modelmapper.Converter<String, Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Category convert(org.modelmapper.spi.MappingContext<String, Category> context) {
        String categoryName = context.getSource();
        return categoryRepository.findById(categoryName)
                .orElse(null);
    }
}
