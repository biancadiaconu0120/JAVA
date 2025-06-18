package com.modul2.learning.CronJob;

import com.modul2.learning.entities.Reservation;
import com.modul2.learning.entities.ReservationStatus;
import com.modul2.learning.repository.ReservationRepository;
import com.modul2.learning.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReservationScheduler {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private NotificationService notificationService;

    // cron job  to cancel expired reservations
    @Scheduled(cron = "0 */1 * * * *") // Runs every 1 minute (TEMPORARY)

    public void cancelExpiredReservations() {
        List<Reservation> expiredReservations = reservationRepository.findByStatusAndStartDateBefore(
                ReservationStatus.PENDING, LocalDate.now());

        for (Reservation reservation : expiredReservations) {
            reservation.setStatus(ReservationStatus.CANCELED);
        }

        reservationRepository.saveAll(expiredReservations);
    }

    // cron job to mark overdue IN_PROGRESS reservations as DELAYED.
    @Scheduled(cron = "0 */1 * * * *") // Runs every 1 minute (TEMPORARY)

    public void markDelayedReservations() {
        List<Reservation> overdueReservations = reservationRepository.findByStatusAndEndDateBefore(
                ReservationStatus.IN_PROGRESS, LocalDate.now());

        for (Reservation reservation : overdueReservations) {
            reservation.setStatus(ReservationStatus.DELAYED);

            // Notify librarian
            notificationService.notifyLibrarian(
                    reservation.getExemplary().getBook().getLibrary().getLibrarian().getEmail(),
                    reservation
            );

            // Notify user
            notificationService.notifyUser(
                    reservation.getUser().getEmail(),
                    reservation
            );
        }

        reservationRepository.saveAll(overdueReservations);
    }
}
