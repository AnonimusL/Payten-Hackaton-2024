package com.payten.hacka.rent_service.service;

import com.payten.hacka.rent_service.dto.CreateReservationDto;
import com.payten.hacka.rent_service.dto.ProductCategoryDto;
import com.payten.hacka.rent_service.dto.ReservationDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationService {

    ReservationDto makeReservation(CreateReservationDto createReservationDto);
    Boolean cancelReservation(UUID reservationId);
    ReservationDto completeReservation(UUID reservationId);
}
