package com.payten.hacka.auth_service.runner;

import com.payten.hacka.auth_service.domain.*;
import com.payten.hacka.auth_service.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

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
            // Creating and saving an Address
            Address address = new Address("Serbia", "Serbia", "Belgrade", "11000", "Knez Mihailova", "6/6", 40.5, -10.3, false, null);
            address = addressRepository.save(address);

            // Creating and saving a Category
            Category category = new Category("Education", "Faculty of Computing", "The best faculty for computer science students");
            category = categoryRepository.save(category);

            // Encoding a password and creating two users
            String ep = passwordEncoder.encode("12345678");
            User user1 = new User("email@gmail.rs", ep, "Ime", "Prezime", Role.COMPANY_MANAGER);
            user1 = userRepository.save(user1);

            User user2 = new User("email2@gmail.rs", ep, "Ime", "Prezime", Role.CLIENT);
            user2 = userRepository.save(user2);

            // Creating a company and assigning users to it
            Company company = new Company("RAF", null, address, null, List.of(user1));
            company = companyRepository.save(company);

            // Linking the users with the company
            user1.setCompany(company);
            userRepository.save(user1);
            user2.setCompany(company);
            userRepository.save(user2);

            // Creating two businesses and assigning them to the company and users
            Business business1 = new Business(category, "RAF", company, List.of(user1));
            Business business2 = new Business(category, "CET", company, List.of(user1));

            Address address2 = new Address("Serbia", "Serbia", "Belgrade", "11000", "Knez Mihailova", "6/5", 40.5, -10.3, true, null);
            address2 = addressRepository.save(address2);

            business1.setAddresses(List.of(address2));

            // Saving businesses
            businessRepository.save(business1);
            businessRepository.save(business2);
        };
    }
}
