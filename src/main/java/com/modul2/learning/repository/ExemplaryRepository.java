package com.modul2.learning.repository;

import com.modul2.learning.entities.Exemplary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplaryRepository extends JpaRepository<Exemplary, Long> {

    List<Exemplary> findByBookId(Long bookId);
    // pt pagination
    Page<Exemplary> findByBookId(Long bookId, Pageable pageable);
}
