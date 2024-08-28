package com.abcrestaurant.restaurantweb.repository;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    int countByBranchAndReservationDate(Branch branch, LocalDate reservationDate);
    int countByBranchAndReservationDateAndReservationTime(Branch branch, LocalDate date, LocalTime time);
}
