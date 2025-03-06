package com.modul2.learning.mapper;

import com.modul2.learning.dto.ReservationDTO;
import com.modul2.learning.entities.Reservation;
import com.modul2.learning.entities.User;
import com.modul2.learning.entities.Exemplary;

public class ReservationMapper {

    public static Reservation reservationDTO2Reservation(ReservationDTO reservationDTO, User user, Exemplary exemplary) {
        Reservation reservation = new Reservation();
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setUser(user);
        reservation.setExemplary(exemplary);
        return reservation;
    }

    public static ReservationDTO reservation2ReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setUserId(reservation.getUser().getId());
        reservationDTO.setExemplaryId(reservation.getExemplary().getId());
        return reservationDTO;
    }
}
