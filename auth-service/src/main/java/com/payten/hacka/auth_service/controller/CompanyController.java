package com.payten.hacka.auth_service.controller;

import com.payten.hacka.auth_service.dto.*;
import com.payten.hacka.auth_service.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @GetMapping
    private ResponseEntity<List<CompanyDto>> allCompanies(){
        return new ResponseEntity<>(companyService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CompanyDetailDto> aboutCompany(@PathVariable("id") UUID companyId){
        return new ResponseEntity<>(companyService.getCompanyDetails(companyId), HttpStatus.OK);
    }
    @PostMapping("/business")
   private ResponseEntity<BusinessDetailDto> registerBusiness(@RequestBody CreateBusinessDto createBusinessDto){
        return new ResponseEntity<>(companyService.addBusiness(createBusinessDto), HttpStatus.OK);
    }

    @PostMapping("/{id}/address")
    private ResponseEntity<CompanyDetailDto> changeCompanyAddress(@PathVariable("id") UUID companyId, @RequestBody AddressDto addressDto){
        return new ResponseEntity<>(companyService.updateCompanyAddress(companyId, addressDto), HttpStatus.OK);
    }
}
