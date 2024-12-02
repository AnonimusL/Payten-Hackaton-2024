package com.payten.hacka.rent_service.repository;

import com.payten.hacka.rent_service.domain.ProductCategory;
import com.payten.hacka.rent_service.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    @Query("SELECT COUNT(r) FROM Reservation r " +
            "WHERE r.product.id = :productCategoryId " +
            "AND ((r.activityFrom BETWEEN :startDate AND :endDate) " +
            "OR (r.activityTo BETWEEN :startDate AND :endDate) " +
            "OR (r.activityFrom <= :startDate AND r.activityTo >= :endDate)) " +
            "AND NOT r.cancelled AND NOT r.completed")
    long countOverlappingReservations(@Param("productCategoryId") UUID productCategoryId,
                                      @Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.product IN :productCategories " +
            "AND ((r.activityFrom BETWEEN :startDate AND :endDate) " +
            "OR (r.activityTo BETWEEN :startDate AND :endDate) " +
            "OR (r.activityFrom <= :startDate AND r.activityTo >= :endDate)) " +
            "AND NOT r.cancelled AND NOT r.completed")
    List<Reservation> findReservationsForProductCategoriesWithinDateRange(List<ProductCategory> productCategories,
                                                                          LocalDateTime startDate,
                                                                          LocalDateTime endDate);

}
