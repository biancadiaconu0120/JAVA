package com.modul2.learning.repository;

import com.modul2.learning.entities.Application;
import com.modul2.learning.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}