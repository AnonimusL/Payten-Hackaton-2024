package com.payten.hacka.rent_service.app_runner;

import com.payten.hacka.rent_service.domain.*;
import com.payten.hacka.rent_service.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Base64;
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
        String photo = "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AREQEREBERAREQEREBERAREQEREBERAREQERED//2Q==";
        byte[] skatesPhoto = Base64.getDecoder().decode(photo);
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
