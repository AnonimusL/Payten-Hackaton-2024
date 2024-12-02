package com.payten.hacka.rent_service.app_runner;

import com.payten.hacka.rent_service.domain.*;
import com.payten.hacka.rent_service.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final RentalUnitRepository rentalUnitRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentRegistryRepository paymentRegistryRepository;

    public DataInitializer(
            ProductRepository productRepository,
            ProductCategoryRepository productCategoryRepository,
            RentalUnitRepository rentalUnitRepository,
            ReservationRepository reservationRepository,
            PaymentRegistryRepository paymentRegistryRepository
    ) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.rentalUnitRepository = rentalUnitRepository;
        this.reservationRepository = reservationRepository;
        this.paymentRegistryRepository = paymentRegistryRepository;
    }

    @Override
    public void run(String... args) {
        Product skates = new Product(
                UUID.randomUUID(),
                "Skates",
                "Sports",
                UUID.randomUUID(),
                UUID.randomUUID(),
                30, // available
                3,  // in use
                false,
                null,
                null
        );
        skates = productRepository.save(skates);

        // Rental Units
        RentalUnit hourly = new RentalUnit(UUID.randomUUID(), "hour", BigDecimal.valueOf(5.00), skates);
        RentalUnit daily = new RentalUnit(UUID.randomUUID(), "day", BigDecimal.valueOf(25.00), skates);
        RentalUnit weekly = new RentalUnit(UUID.randomUUID(), "week", BigDecimal.valueOf(100.00), skates);

        rentalUnitRepository.saveAll(List.of(hourly, daily, weekly));

        // Categories
        ProductCategory men = new ProductCategory(
                UUID.randomUUID(),
                skates,
                "Gender",
                "Men",
                20,
                null
        );

        ProductCategory women = new ProductCategory(
                UUID.randomUUID(),
                skates,
                "Gender",
                "Women",
                15,
                null
        );

        ProductCategory size43 = new ProductCategory(
                UUID.randomUUID(),
                skates,
                "Size",
                "43",
                10,
                men
        );

        ProductCategory size38 = new ProductCategory(
                UUID.randomUUID(),
                skates,
                "Size",
                "38",
                5,
                women
        );
        productCategoryRepository.saveAll(List.of(men, women, size43, size38));

        System.out.println("Data Initialized Successfully");
    }
}
