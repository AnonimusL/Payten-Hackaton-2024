package com.payten.hacka.rent_service.app_runner;

import com.payten.hacka.rent_service.domain.*;
import com.payten.hacka.rent_service.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
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

    private byte[] getImageBytes(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        return Files.readAllBytes(imageFile.toPath());
    }

    @Override
    public void run(String... args) throws IOException {
        // Create Product entity
        byte[] skatesPhoto = getImageBytes("C:\\Users\\mijan\\OneDrive\\Desktop\\payten hackaton 2024\\Payten-Hackaton-2024\\rent-service\\src\\main\\resources\\static\\images\\skates.jpg");
        Product skates = new Product(
                UUID.randomUUID(),  // product id
                "Klizaljke",
                "Skates",
                "Sport",
                "Sports",
                UUID.randomUUID(),  // businessId (this would typically be a valid business ID from your DB)
                UUID.randomUUID(),  // addressId (this would typically be a valid address ID from your DB)
                skatesPhoto,
                30, // available quantity
                3,  // in-use quantity
                false, // not deleted
                null,  // photo (optional)
                null   // reserved list (optional)
        );
        skates = productRepository.save(skates);

        // Rental Units for the product
        RentalUnit hourly = new RentalUnit(UUID.randomUUID(), "sati", "hour", BigDecimal.valueOf(5.00), skates);
        RentalUnit daily = new RentalUnit(UUID.randomUUID(), "dnevno", "day", BigDecimal.valueOf(25.00), skates);
        RentalUnit weekly = new RentalUnit(UUID.randomUUID(), "nedeljno","week", BigDecimal.valueOf(100.00), skates);

        rentalUnitRepository.saveAll(List.of(hourly, daily, weekly));

        // Product Categories
        ProductCategory men = new ProductCategory(
                UUID.randomUUID(),
                skates,
                "Gender",
                "Men",
                20,
                null  // No parent category
        );

        ProductCategory women = new ProductCategory(
                UUID.randomUUID(),
                skates,
                "Gender",
                "Women",
                15,
                null  // No parent category
        );

        ProductCategory size43 = new ProductCategory(
                UUID.randomUUID(),
                skates,
                "Size",
                "43",
                10,
                men // Parent category is "Men"
        );

        ProductCategory size38 = new ProductCategory(
                UUID.randomUUID(),
                skates,
                "Size",
                "38",
                5,
                women // Parent category is "Women"
        );

        // Save categories
        productCategoryRepository.saveAll(List.of(men, women, size43, size38));

        System.out.println("Data Initialized Successfully");
    }
}
