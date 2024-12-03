package com.payten.hacka.rent_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDto {
    private UUID id;
    private ProductCategoryDto product;
    private RentalUnitDto rentalUnit;
    private int rentalAmount;
    private int prodAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime reservationTime;
    private boolean completed;
    private boolean cancelled;
}
