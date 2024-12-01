package com.payten.hacka.auth_service.controller;

import com.payten.hacka.auth_service.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;
}
