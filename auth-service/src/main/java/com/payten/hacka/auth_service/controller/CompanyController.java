package com.payten.hacka.auth_service.controller;

import com.payten.hacka.auth_service.dto.BusinessDto;
import com.payten.hacka.auth_service.dto.CreateBusinessDto;
import com.payten.hacka.auth_service.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @PostMapping("/business")
   private ResponseEntity<BusinessDto> addBusiness(@RequestBody CreateBusinessDto createBusinessDto){
        return new ResponseEntity<>(companyService.addBusiness(createBusinessDto), HttpStatus.OK);
    }
}
