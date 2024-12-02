package com.payten.hacka.rent_service.domain;

import com.payten.hacka.rent_service.dto.ProductCategoryDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private UUID id;
    @ManyToOne
    private ProductCategory product;
    @ManyToOne
    private RentalUnit rentalUnit;
    private int amount;
    private int productAmount;
    private LocalDateTime activityFrom;
    private LocalDateTime activityTo;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(nullable = true)
    private LocalDateTime deletedAt;
    private LocalDateTime reservationTime;
    private boolean completed;
    private boolean cancelled;
}
