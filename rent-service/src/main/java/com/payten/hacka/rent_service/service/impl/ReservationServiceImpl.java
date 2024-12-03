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
import com.payten.hacka.rent_service.service.rmq.RMQConfig;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private RabbitTemplate rabbitTemplate;

    @Override
    public ReservationDto makeReservation(CreateReservationDto createReservationDto) {
        RentalUnit rentalUnit = rentalUnitRepository.findById(createReservationDto.getRentalUnit()).orElseThrow(()-> new NotFoundException(String.format("rental unit not found")));
        ProductCategory productCategory = productCategoryRepository.findById(createReservationDto.getProduct()).orElseThrow(() -> new NotFoundException("product instance not found"));

        if(productAvailability(productCategory.getId(), createReservationDto.getReservedFrom(), calculateEndDateTime(createReservationDto.getReservedFrom(), rentalUnit, createReservationDto.getProductAmount())) < productCategory.getProduct().getMaxNumForRent()){
            Reservation reservation = modelMapper.map(createReservationDto, Reservation.class);
            reservation = reservationRepository.save(reservation);
            sendEmail();
            return modelMapper.map(reservation, ReservationDto.class);
        }else throw new NotFoundException("Product not available");

    }

    private void sendEmail() {
        try {
            String message = createJsonMessage();
            this.rabbitTemplate.convertAndSend("application_exchange", "rental.rent", message);
        } catch (Exception e) {
            throw new RuntimeException("Cannot send mail");
        }
    }

    private String createJsonMessage() {
        return "{"
                + "\"toEmail\": \"mijanajdic@gmail.com\","
                + "\"messageSubject\": \"Rezervacija\","
                + "\"messageContent\": \"Ovo je vas QR kod\","
                + "\"qrCode\": \"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\""
                + "}";
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
            case "sati":
                return startDateTime.plusHours(duration);
            case "dnevno":
                return startDateTime.plusDays(duration);
            case "nedeljno":
                return startDateTime.plusWeeks(duration);
            default:
                throw new IllegalArgumentException("Unsupported rental unit: " + rentalUnit.getUnitName());
        }
    }
    @Override
    public Boolean cancelReservation(UUID reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()->new NotFoundException(String.format("reservation with id: %s not found", reservationId)));
        reservation.setCancelled(true);
        reservation.setDeletedAt(LocalDateTime.now());
        reservationRepository.save(reservation);
        return true;
    }

    @Override
    public ReservationDto completeReservation(UUID reservationId) {

        return null;
    }

    @Override
    public ReservationDto getReservation(UUID reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()->new NotFoundException(String.format("reservation with id: %s not found", reservationId)));
        return modelMapper.map(reservation, ReservationDto.class);
    }


}
