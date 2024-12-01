package com.payten.hacka.auth_service.controller;

import com.payten.hacka.auth_service.dto.BusinessDto;
import com.payten.hacka.auth_service.dto.CompanyDto;
import com.payten.hacka.auth_service.dto.CreateBusinessDto;
import com.payten.hacka.auth_service.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @GetMapping("/{id}")
    private ResponseEntity<CompanyDto> aboutCompany(@PathVariable("id") UUID companyId){
        return new ResponseEntity<>(companyService.getCompanyDetails(companyId), HttpStatus.OK);
    }
    @PostMapping("/business")
   private ResponseEntity<BusinessDto> registerBusiness(@RequestBody CreateBusinessDto createBusinessDto){
        return new ResponseEntity<>(companyService.addBusiness(createBusinessDto), HttpStatus.OK);
    }
}
