package com.payten.hacka.rent_service.controller;

import com.payten.hacka.rent_service.dto.CreateReservationDto;
import com.payten.hacka.rent_service.dto.ReservationDto;
import com.payten.hacka.rent_service.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody CreateReservationDto createReservationDto){
        return new ResponseEntity<>(reservationService.makeReservation(createReservationDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> cancelReservation(@PathVariable("id") UUID reservationId){
        return new ResponseEntity<>(reservationService.cancelReservation(reservationId), HttpStatus.OK);
    }
}
