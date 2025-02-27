package com.modul2.learning.service;

import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.Exemplary;
import com.modul2.learning.repository.BookRepository;
import com.modul2.learning.repository.ExemplaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.ArrayList;
import java.util.List;

@Service
public class ExemplaryService {

    @Autowired
    private ExemplaryRepository exemplaryRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Exemplary> createMultipleExemplars(Long bookId, int numberOfExemplars, Exemplary exemplary) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));

        List<Exemplary> exemplars = new ArrayList<>();

        for (int i = 0; i < numberOfExemplars; i++) {
            Exemplary exemplar = new Exemplary();
            exemplar.setBook(book);
            exemplar.setPublisher(exemplary.getPublisher());
            exemplar.setMaximumDurationReservation(exemplary.getMaximumDurationReservation());
            exemplars.add(exemplar);
        }

        return exemplaryRepository.saveAll(exemplars);
    }

    public List<Exemplary> getExemplarsByBook(Long bookId) {
        return exemplaryRepository.findByBookId(bookId);
    }

    public Page<Exemplary> getPaginatedExemplarsByBook(Long bookId, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return exemplaryRepository.findByBookId(bookId, pageable);
    }

    public void deleteExemplary(Long id) {
        exemplaryRepository.deleteById(id);
    }
}
