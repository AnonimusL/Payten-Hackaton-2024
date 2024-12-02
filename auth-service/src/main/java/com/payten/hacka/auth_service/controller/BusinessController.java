package com.payten.hacka.auth_service.controller;

import com.payten.hacka.auth_service.dto.BusinessDto;
import com.payten.hacka.auth_service.service.BusinessService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/business")
@AllArgsConstructor
public class BusinessController {
    private BusinessService businessService;

    @GetMapping
    public ResponseEntity<List<BusinessDto>> getBusinesses(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) UUID addressId) {
        List<BusinessDto> businesses = businessService.getAllBusinesses(categoryName, addressId);
        return ResponseEntity.ok(businesses);
    }
}
