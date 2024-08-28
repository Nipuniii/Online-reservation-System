package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.model.Reservation;
import com.abcrestaurant.restaurantweb.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean checkTableAvailability(Branch branch, LocalDate date, LocalTime time, int numberOfPeople) {
        // Calculate the maximum tables that can be booked at the branch
        int totalReservationsForDate = reservationRepository.countByBranchAndReservationDate(branch, date);

        if (totalReservationsForDate >= branch.getMaxTables()) {
            return false; // No tables available
        }

        // Additionally, you can check for a specific time slot if needed
        int reservationsForTimeSlot = reservationRepository.countByBranchAndReservationDateAndReservationTime(branch, date, time);

        // Logic to determine if the time slot is available
        if (reservationsForTimeSlot > 0) {
            return false; // Time slot is already booked
        }

        return true; // Table is available for the requested date and time
    }

    public void bookTable(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }
}
