package com.modul2.learning.repository;

import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.Exemplary;
import com.modul2.learning.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = """
       SELECT book FROM Book book
       WHERE (:author IS NULL OR LOWER(book.author) LIKE LOWER(CONCAT('%', :author, '%')))
       AND (:title IS NULL OR LOWER(book.title) LIKE LOWER(CONCAT('%', :title, '%')))
       """)
    List<Book> findBooks(String author,String title);


    @Query(value = """
                SELECT * FROM exemplary 
                WHERE book_id = :bookId
                AND id NOT IN (
                    SELECT exemplary_id FROM reservation
                    WHERE NOT (end_date < :startDate OR start_date > :endDate)
                )
                LIMIT 1
            """, nativeQuery = true)
    Optional<Exemplary> findFirstAvailableExemplary(Long bookId, LocalDate startDate, LocalDate endDate);

}
