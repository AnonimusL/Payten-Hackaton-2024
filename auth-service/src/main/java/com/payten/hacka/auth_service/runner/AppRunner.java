package com.payten.hacka.auth_service.runner;

import com.payten.hacka.auth_service.domain.*;
import com.payten.hacka.auth_service.repository.*;
import lombok.AllArgsConstructor;
import org.hibernate.Cache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class AppRunner {

    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private CompanyRepository companyRepository;
    private AddressRepository addressRepository;
    private BusinessRepository businessRepository;
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner loadData() {
        return args -> {
            Address address = new Address("Serbia", "Belgrade",  "11000", "Knez Mihailova", "6/6", null);
            address = addressRepository.save(address);

            Category category = new Category("Education", "Faculty of Computing");
            category = categoryRepository.save(category);

            String ep = passwordEncoder.encode("12345678");
            User user = new User("email@gmail.rs", ep, "Ime", "Prezime", Role.COMPANY_MANAGER);
            userRepository.save(user);

            User user2 = new User("email2@gmail.rs", ep, "Ime", "Prezime", Role.CLIENT);
            userRepository.save(user2);

            Company company = new Company("RAF", address, null, List.of(user));
            companyRepository.save(company);

            user.setCompany(company);
            userRepository.save(user);

            Business business1 = new Business(category, "RAF", company, List.of(user));
            Business business2 = new Business(category, "CET", company, List.of(user));
            business1.setAddresses(List.of(address));

            businessRepository.save(business1);
            businessRepository.save(business2);

        };
    }
}
