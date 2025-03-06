package com.modul2.learning.dto;

import com.modul2.learning.entities.ReservationStatus;

import java.time.LocalDate;

public class ReservationDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;
    private Long userId;
    private Long exemplaryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExemplaryId() {
        return exemplaryId;
    }

    public void setExemplaryId(Long exemplaryId) {
        this.exemplaryId = exemplaryId;
    }
}
