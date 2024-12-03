package com.payten.hacka.auth_service.runner;

import com.payten.hacka.auth_service.domain.*;
import com.payten.hacka.auth_service.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

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
    @Transactional
    CommandLineRunner loadData() {
        return args -> {
            // Creating and saving an Address
            Address address = new Address("Serbia", "Serbia", "Belgrade", "11000", "Knez Mihailova", "6/6", 40.5, -10.3, false, null);
            address = addressRepository.save(address);

            // Creating and saving a Category
            Category category = new Category("Pokloni", "Present", "Odaberite poklon za vase najdraze");
            category = categoryRepository.save(category);

            Category category1 = new Category("Zabava", "Entertainment", "Zabavite se");
            category1 = categoryRepository.save(category1);

            // Encoding a password and creating two users
            String ep = passwordEncoder.encode("12345678");
            User user1 = new User("email@gmail.rs", ep, "Ime", "Prezime", Role.COMPANY_MANAGER);
            user1 = userRepository.save(user1);

            User user2 = new User("email2@gmail.rs", ep, "Ime", "Prezime", Role.COMPANY_MANAGER);
            user2 = userRepository.save(user2);

            User user3 = new User("email3@gmail.rs", ep, "Ime", "Prezime", Role.COMPANY_MANAGER);
            user3 = userRepository.save(user3);

            // Creating a company and assigning users to it
            Company company = new Company("Flora DOO", "Flora DOO", address, null, List.of(user1));
            company = companyRepository.save(company);
            Company company1 = new Company("Travel Buddy", "Travel Buddy", address, null, List.of(user2));
            company1 = companyRepository.save(company1);
            Company company2 = new Company("Klizanje-Tas", "Iceskating-Tas", address, null, List.of(user3));
            company2 = companyRepository.save(company2);

            // Linking the users with the company
            user1.setCompany(company);
            userRepository.save(user1);
            user2.setCompany(company1);
            userRepository.save(user2);
            user3.setCompany(company2);
            userRepository.save(user3);

            // Creating two businesses and assigning them to the company and users
            Business business1 = new Business(category, "Cvecara Maja", company, List.of(user1));
            business1.setId(UUID.fromString("28e1fbbd-9a93-4d02-b6f6-26c7bc0e1cdb"));
            Business business2 = new Business(category, "Prolece", company, List.of(user1));
            business2.setId(UUID.fromString("d0a34f58-d3ee-4c6a-a5f5-e183a03807c7"));
            Business business3 = new Business(category1, "Slusalice", company1, List.of(user1));
            business3.setId(UUID.fromString("37ca6872-1d7b-48f8-897d-21b264acdef9"));
            Business business4 = new Business(category1, "Klizanje na ledu", company2, List.of(user2));
            business4.setId(UUID.fromString("25ebb9ec-2dc3-46fb-b9e0-753246958458"));

             Address address2 = new Address("Serbia", "Serbia", "Belgrade", "11000", "Knez Mihailova", "6/5", 20.4633, 44.8176, true, null);
             address2.setId(UUID.fromString("6188c025-7f49-4be1-a27c-ad1cc9e5d9c1"));

             Address address3 = new Address("Serbia", "Serbia", "Belgrade", "11000", "Hram Svetog Save", "10", 20.4590, 44.8176,  true, null);
             address3.setId(UUID.fromString("787fb272-97e5-430a-9d9e-4506f8816dad"));

             Address address4 = new Address("Serbia", "Serbia", "Belgrade", "11000", "Trg Republike", "45b", 20.4633, 44.8176,  true, null);
             address4.setId(UUID.fromString("fcdb39ad-ca7f-4e11-abe8-706ce5955654"));

             Address address5 = new Address("Serbia", "Serbia", "Belgrade", "11000", "Muzej Nikole Tesle", "110", 20.4630, 44.8175,  true, null);
             address5.setId(UUID.fromString("754ab393-94bb-4b16-ac8c-6133d7fde3c9"));

            Address address6 = new Address("Serbia", "Serbia", "Belgrade", "11000", "Tasmajdan", "20", 20.4727, 44.8115,  true, null);
            address6.setId(UUID.fromString("4f6e9904-59b6-4fde-bb95-3b618627ac45"));

            business1.setAddresses(List.of(address2, address4));
            business2.setAddresses(List.of(address3, address4));
            business3.setAddresses(List.of(address3, address5));
            business4.setAddresses(List.of(address4));

            addressRepository.save(address2);
            addressRepository.save(address3);
            addressRepository.save(address4);
            addressRepository.save(address5);
            addressRepository.save(address6);

            // Saving businesses
            businessRepository.save(business1);
            businessRepository.save(business2);
            businessRepository.save(business3);
            businessRepository.save(business4);
        };
    }
}
