package com.payten.hacka.rent_service.controller;

import com.payten.hacka.rent_service.dto.CreateProductDto;
import com.payten.hacka.rent_service.dto.ProductDetailDto;
import com.payten.hacka.rent_service.dto.ProductDto;
import com.payten.hacka.rent_service.dto.ProductInstanceDto;
import com.payten.hacka.rent_service.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("{id}/instance")
    public ResponseEntity<ProductDetailDto> addProductInstance(@PathVariable("id") UUID productId, @RequestBody ProductInstanceDto productInstanceDto){
        return new ResponseEntity<>(productService.addProductInstance(productId, productInstanceDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDto> getProduct(@PathVariable("id") UUID productId){
        return new ResponseEntity<>(productService.getProductInfo(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable("id") UUID locationId){
        return new ResponseEntity<>(productService.findAllByLocation(locationId), HttpStatus.OK);
    }

}
