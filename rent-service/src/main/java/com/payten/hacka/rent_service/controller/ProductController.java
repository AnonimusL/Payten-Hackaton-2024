package com.payten.hacka.rent_service.controller;

import com.payten.hacka.rent_service.dto.CreateProductDto;
import com.payten.hacka.rent_service.dto.ProductCategoryDto;
import com.payten.hacka.rent_service.dto.ProductDetailDto;
import com.payten.hacka.rent_service.dto.ProductDto;
import com.payten.hacka.rent_service.service.ProductService;
import com.payten.hacka.rent_service.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDetailDto> addProduct(@RequestBody CreateProductDto createProductDto){
        return new ResponseEntity<>(productService.createProduct(createProductDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDto> getProduct(@PathVariable("id") UUID productId){
        return new ResponseEntity<>(productService.getProductInfo(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/available")
    public ResponseEntity<List<ProductCategoryDto>> getAvailableInstancesForProduct(@PathVariable("id") UUID productId, @RequestParam LocalDateTime from, @RequestParam LocalDateTime to){
        return new ResponseEntity<>(productService.getAvailableProducts(productId, from, to), HttpStatus.OK);
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable("id") UUID locationId){
        return new ResponseEntity<>(productService.findAllByLocation(locationId), HttpStatus.OK);
    }

}
