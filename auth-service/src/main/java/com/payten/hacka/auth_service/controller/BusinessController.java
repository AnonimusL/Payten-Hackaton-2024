package com.payten.hacka.auth_service.controller;

import com.payten.hacka.auth_service.dto.BusinessDetailDto;
import com.payten.hacka.auth_service.dto.BusinessDto;
import com.payten.hacka.auth_service.service.BusinessService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business")
@AllArgsConstructor
public class BusinessController {
    private BusinessService businessService;

    @GetMapping
    private ResponseEntity<List<BusinessDto>> allBusinesses(){
        return new ResponseEntity<>(businessService.getAll(), HttpStatus.OK);
    }
}
