package com.payten.hacka.rent_service.service.impl;

import com.payten.hacka.rent_service.domain.Product;
import com.payten.hacka.rent_service.domain.ProductInstance;
import com.payten.hacka.rent_service.domain.RentalUnit;
import com.payten.hacka.rent_service.dto.*;
import com.payten.hacka.rent_service.exceptions.NotFoundException;
import com.payten.hacka.rent_service.repository.ProductInstanceRepository;
import com.payten.hacka.rent_service.repository.ProductRepository;
import com.payten.hacka.rent_service.repository.RentalUnitRepository;
import com.payten.hacka.rent_service.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductInstanceRepository productInstanceRepository;
    private RentalUnitRepository rentalUnitRepository;
    private ModelMapper modelMapper;
    @Override
    public ProductDetailDto createProduct(CreateProductDto createProductDto) {
        Product product = modelMapper.map(createProductDto, Product.class);
        List<RentalUnit> rentalUnits = createProductDto.getRentalUnits().stream().map(rentalUnitDto -> modelMapper.map(rentalUnitDto, RentalUnit.class)).collect(Collectors.toList());
        rentalUnits = rentalUnitRepository.saveAll(rentalUnits);
        product.setRentalUnits(rentalUnits);
        final Product fProduct = productRepository.save(product);
        List<ProductInstance> productInstances = createProductDto.getProductInstances().stream()
                .map(productInstance -> modelMapper.map(productInstance, ProductInstance.class))
                .peek(productInstance -> productInstance.setProduct(fProduct))
                .collect(Collectors.toList());
        productInstances = productInstanceRepository.saveAll(productInstances);
        ProductDetailDto productDetailDto = modelMapper.map(product, ProductDetailDto.class);
        productDetailDto.setProductInstances(productInstances.stream().map(productInstance -> modelMapper.map(productInstance, ProductInstanceDto.class)).collect(Collectors.toList()));
        productDetailDto.setRentalUnits(rentalUnits.stream().map(rentalUnit -> modelMapper.map(rentalUnit, RentalUnitDto.class)).collect(Collectors.toList()));

        return productDetailDto;
    }

    @Override
    public ProductDetailDto addProductInstance(UUID productId, ProductInstanceDto productInstanceDto) {
        Product product = productRepository.findById(productId).orElseThrow(()->new NotFoundException(String.format("Product with id: %s not found", productId)));
        ProductInstance productInstance = modelMapper.map(productInstanceDto, ProductInstance.class);
        productInstance.setProduct(product);
        List<ProductInstance> productInstances = productInstanceRepository.findAllByProduct(product);
        productInstance = productInstanceRepository.save(productInstance);
        productInstances.add(productInstance);

        ProductDetailDto productDetailDto = modelMapper.map(product, ProductDetailDto.class);
        productDetailDto.setProductInstances(productInstances.stream().map(productInst -> modelMapper.map(productInst, ProductInstanceDto.class)).collect(Collectors.toList()));
        productDetailDto.setRentalUnits(product.getRentalUnits().stream().map(rentalUnit -> modelMapper.map(rentalUnit, RentalUnitDto.class)).collect(Collectors.toList()));

        return productDetailDto;
    }

    @Override
    public ProductDetailDto getProductInfo(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(String.format("product with id: %s not found", productId)));
        List<ProductInstance> productInstances = productInstanceRepository.findAllByProduct(product);
        ProductDetailDto productDetailDto = modelMapper.map(product, ProductDetailDto.class);
        productDetailDto.setRentalUnits(product.getRentalUnits().stream().map(rentalUnit -> modelMapper.map(rentalUnit, RentalUnitDto.class))
                .collect(Collectors.toList()));
        productDetailDto.setProductInstances(productInstances.stream().map(productInstance -> modelMapper.map(productInstance, ProductInstanceDto.class))
                .collect(Collectors.toList()));
        return productDetailDto;
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }
}
