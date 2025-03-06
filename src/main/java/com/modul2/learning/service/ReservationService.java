package com.modul2.learning.service;

import com.modul2.learning.dto.ReservationDTO;
import com.modul2.learning.entities.*;
import com.modul2.learning.mapper.ReservationMapper;
import com.modul2.learning.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    public Reservation createReservation(Long userId, String title, String author, LocalDate startDate, LocalDate endDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Book book = reservationRepository.findBooks(author, title).stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        Exemplary exemplary = reservationRepository.findFirstAvailableExemplary(book.getId(), startDate, endDate)
                .orElseThrow(() -> new IllegalStateException("No available exemplary for this book in that period"));

        // Create a new reservation and set values using setters
        Reservation reservation = new Reservation();
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setUser(user);
        reservation.setExemplary(exemplary);

        return reservationRepository.save(reservation);
    }

}
