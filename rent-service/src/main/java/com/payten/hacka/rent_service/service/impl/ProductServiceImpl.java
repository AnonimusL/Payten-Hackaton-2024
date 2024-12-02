package com.payten.hacka.rent_service.service.impl;

import com.payten.hacka.rent_service.domain.Product;
import com.payten.hacka.rent_service.domain.ProductCategory;
import com.payten.hacka.rent_service.domain.RentalUnit;
import com.payten.hacka.rent_service.domain.Reservation;
import com.payten.hacka.rent_service.dto.*;
import com.payten.hacka.rent_service.exceptions.NotFoundException;
import com.payten.hacka.rent_service.repository.ProductCategoryRepository;
import com.payten.hacka.rent_service.repository.ProductRepository;
import com.payten.hacka.rent_service.repository.RentalUnitRepository;
import com.payten.hacka.rent_service.repository.ReservationRepository;
import com.payten.hacka.rent_service.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private RentalUnitRepository rentalUnitRepository;
    private ProductCategoryRepository productCategoryRepository;
    private ReservationRepository reservationRepository;
    private ModelMapper modelMapper;
    @Override
    public ProductDetailDto createProduct(CreateProductDto createProductDto) {
        Product product = modelMapper.map(createProductDto, Product.class);
        List<RentalUnit> rentalUnits = createProductDto.getRentalUnits().stream().map(rentalUnitDto -> modelMapper.map(rentalUnitDto, RentalUnit.class)).collect(Collectors.toList());
        rentalUnits = rentalUnitRepository.saveAll(rentalUnits);
        product.setRentalUnits(rentalUnits);

        List<ProductCategory> productCategories = createProductDto.getProductSupportedCategories().stream()
                .map(productCategoryDto -> modelMapper.map(productCategoryDto, ProductCategory.class))
                .collect(Collectors.toList());

        productCategories = productCategoryRepository.saveAll(productCategories);
        product.setProductSupportedCategories(productCategories);

        final Product fProduct = productRepository.save(product);

        ProductDetailDto productDetailDto = modelMapper.map(product, ProductDetailDto.class);
        productDetailDto.setRentalUnits(rentalUnits.stream().map(rentalUnit -> modelMapper.map(rentalUnit, RentalUnitDto.class)).collect(Collectors.toList()));
        return productDetailDto;
    }

    @Override
    public ProductDetailDto getProductInfo(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(String.format("product with id: %s not found", productId)));
        ProductDetailDto productDetailDto = modelMapper.map(product, ProductDetailDto.class);
        productDetailDto.setRentalUnits(product.getRentalUnits().stream().map(rentalUnit -> modelMapper.map(rentalUnit, RentalUnitDto.class))
                .collect(Collectors.toList()));

        /**
        List<ProductCategory> productCategories = productCategoryRepository.findByProduct(product);
        Map<String, List<ProductCategoryDto>> categoryMap = new HashMap<>();
        for(ProductCategory pc : productCategories){
            ProductCategoryDto pcd = modelMapper.map(pc, ProductCategoryDto.class);
            categoryMap.put(pcd.getName(), new ArrayList<>());

            if(pc.getDependentCategories()==null) continue;

            ProductCategoryDto pcDepend = modelMapper.map(pc.getDependentCategories(), ProductCategoryDto.class);

            if(!categoryMap.containsKey(pcDepend.getName())) {
                categoryMap.put(pcDepend.getName(), new ArrayList<>());
            }

            categoryMap.get(pcDepend.getName()).add(pcd);
        }
         */

        productDetailDto.setCategories(getCategoryHierarchy(product));

        return productDetailDto;
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByLocation(UUID locationId) {
        return productRepository.findAllByAddressId(locationId).stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductCategoryDto> getCategoryHierarchy(Product product) {
        List<ProductCategory> categories = productCategoryRepository.findByProduct(product);
        Map<UUID, ProductCategory> categoryMap = categories.stream()
                .collect(Collectors.toMap(ProductCategory::getId, c -> c));

        // Separate root categories (no parent)
        List<ProductCategory> rootCategories = categories.stream()
                .filter(c -> c.getParentCategory() == null)
                .collect(Collectors.toList());

        // Recursively build the hierarchy with level numbers
        List<ProductCategoryDto> hierarchy = new ArrayList<>();
        for (ProductCategory root : rootCategories) {
            buildHierarchy(root, hierarchy, categoryMap, 1); // Start with level 1 for root categories
        }
        return hierarchy;
    }

    private void buildHierarchy(ProductCategory category, List<ProductCategoryDto> hierarchy,
                                Map<UUID, ProductCategory> categoryMap, int level) {
        // Create DTO for the category with its level
        ProductCategoryDto categoryDto = new ProductCategoryDto(
                category.getId(),
                category.getName(),
                category.getCatValue(),
                category.getAmount(),
                0,
                category.getAmount(),
                level,
                new ArrayList<>()
        );

        hierarchy.add(categoryDto);

        // Recursively build the dependents with incremented level
        if (category.getDependentCategories() != null) {
            for (ProductCategory dependent : category.getDependentCategories()) {
                buildHierarchy(dependent, categoryDto.getDependentCategories(), categoryMap, level + 1);
            }
        }
    }

    public List<ProductCategoryDto> getAvailableProducts(UUID productId, LocalDateTime startDate, LocalDateTime endDate) {
        Product product = productRepository.findById(productId).orElseThrow(()->new NotFoundException("product not found"));

        List<ProductCategory> productCategories = productCategoryRepository.findByProduct(product);
        List<Reservation> reservations = reservationRepository.findReservationsForProductCategoriesWithinDateRange(productCategories, startDate, endDate);

        Map<UUID, ProductCategoryDto> availableProductsMap = productCategories.stream()
                .map(productCategory -> {
                    ProductCategoryDto dto = modelMapper.map(productCategory, ProductCategoryDto.class);
                    dto.setAvailable(productCategory.getAmount());
                    return dto;
                })
                .collect(Collectors.toMap(ProductCategoryDto::getId, dto -> dto));

        for(Reservation reservation : reservations){
            availableProductsMap.get(reservation.getProduct()).setAvailable(availableProductsMap.get(reservation.getProduct()).getAvailable()-reservation.getProductAmount());
            availableProductsMap.get(reservation.getProduct()).setInUse(availableProductsMap.get(reservation.getProduct()).getInUse()+reservation.getProductAmount());
        }

        // Return the list of available products
        return availableProductsMap.values().stream().toList();
    }
}
