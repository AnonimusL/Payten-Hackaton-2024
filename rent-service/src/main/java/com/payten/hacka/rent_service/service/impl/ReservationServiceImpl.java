package com.payten.hacka.rent_service.service.impl;

import com.payten.hacka.rent_service.domain.Product;
import com.payten.hacka.rent_service.domain.ProductCategory;
import com.payten.hacka.rent_service.domain.RentalUnit;
import com.payten.hacka.rent_service.domain.Reservation;
import com.payten.hacka.rent_service.dto.CreateReservationDto;
import com.payten.hacka.rent_service.dto.ProductCategoryDto;
import com.payten.hacka.rent_service.dto.ProductDto;
import com.payten.hacka.rent_service.dto.ReservationDto;
import com.payten.hacka.rent_service.exceptions.NotFoundException;
import com.payten.hacka.rent_service.repository.ProductCategoryRepository;
import com.payten.hacka.rent_service.repository.ProductRepository;
import com.payten.hacka.rent_service.repository.RentalUnitRepository;
import com.payten.hacka.rent_service.repository.ReservationRepository;
import com.payten.hacka.rent_service.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private RentalUnitRepository rentalUnitRepository;
    private ReservationRepository reservationRepository;
    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private ModelMapper modelMapper;

    @Override
    public ReservationDto makeReservation(CreateReservationDto createReservationDto) {
        RentalUnit rentalUnit = rentalUnitRepository.findById(createReservationDto.getRentalUnit()).orElseThrow(()-> new NotFoundException(String.format("rental unit not found")));
        ProductCategory productCategory = productCategoryRepository.findById(createReservationDto.getProduct()).orElseThrow(() -> new NotFoundException("product instance not found"));

        if(productAvailability(productCategory.getId(), createReservationDto.getReservedFrom(), calculateEndDateTime(createReservationDto.getReservedFrom(), rentalUnit, createReservationDto.getAmount())) < productCategory.getAmount()){
            Reservation reservation = modelMapper.map(createReservationDto, Reservation.class);
            reservation = reservationRepository.save(reservation);
            return modelMapper.map(reservation, ReservationDto.class);
        }else throw new NotFoundException("Product not available");

    }

    public long productAvailability(UUID productCategoryId, LocalDateTime startDate, LocalDateTime endDate) {
        long overlappingReservationsCount = reservationRepository.countOverlappingReservations(productCategoryId, startDate, endDate);

        return overlappingReservationsCount;
    }

    public LocalDateTime calculateEndDateTime(LocalDateTime startDateTime, RentalUnit rentalUnit, int duration) {
        switch (rentalUnit.getUnitName().toLowerCase()) {
            case "hour":
                return startDateTime.plusHours(duration);
            case "day":
                return startDateTime.plusDays(duration);
            case "week":
                return startDateTime.plusWeeks(duration);
            default:
                throw new IllegalArgumentException("Unsupported rental unit: " + rentalUnit.getUnitName());
        }
    }
    @Override
    public Boolean cancelReservation(UUID reservationId) {

        return null;
    }

    @Override
    public ReservationDto completeReservation(UUID reservationId) {
        return null;
    }



}
