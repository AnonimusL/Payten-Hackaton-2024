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
public class CreateReservationDto {
    private UUID product;
    private UUID rentalUnit;
    private int rentalAmount;
    private int productAmount;
    private LocalDateTime reservedFrom;
}
