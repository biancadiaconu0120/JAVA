package com.modul2.learning.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "exemplary", schema = "public")
public class Exemplary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name= "version")
    private Integer version;

//    @Column(name = "update_time", nullable = false)
//    private LocalDate updateTime;

    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @Column(name = "MAXIMUM_DURATION_RESERVATION", nullable = false)
    private int maximumDurationReservation;

    //o carte are mai multe exemplare
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    //un exemplar are mai multe reservari
    //tre sa adaug eu LAZY si EAGER si orphanRemoval=true
    @OneToMany(mappedBy = "exemplary", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> reservations;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getMaximumDurationReservation() {
        return maximumDurationReservation;
    }

    public void setMaximumDurationReservation(int maximumDurationReservation) {
        this.maximumDurationReservation = maximumDurationReservation;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
