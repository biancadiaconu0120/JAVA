package com.modul2.learning.dto;

public class ExemplaryDTO {
    private Long id;
    private String publisher;
    private int maximumDurationReservation;
    private Long bookId;

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

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
