package com.modul2.learning.controller;

import com.modul2.learning.dto.BookDTO;
import com.modul2.learning.dto.ReservationDTO;
import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.Reservation;
import com.modul2.learning.mapper.BookMapper;
import com.modul2.learning.mapper.ReservationMapper;
import com.modul2.learning.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestParam Long userId, @RequestParam String title, @RequestParam String author, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {

        Reservation reservation = reservationService.createReservation(userId, title, author, startDate, endDate);
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(reservation));
    }


    @PutMapping("/{reservationId}")
    public ResponseEntity<?> updateReservation(@PathVariable Long reservationId, @RequestParam Long librarianId, @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = ReservationMapper.reservationDTO2Reservation(reservationDTO);
        Reservation updatedReservation = reservationService.updateReservation(reservationId, librarianId, reservation);
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(updatedReservation));
    }



}
