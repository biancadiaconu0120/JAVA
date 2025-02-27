package com.modul2.learning.mapper;

import com.modul2.learning.dto.BookDTO;
import com.modul2.learning.dto.ExemplaryDTO;
import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.BookCategory;
import com.modul2.learning.entities.Exemplary;

public class ExemplaryMapping {
    public static Exemplary exemplaryDTO2Exemplary(ExemplaryDTO dto) {
        Exemplary exemplary = new Exemplary();
        exemplary.setId(dto.getId());
        exemplary.setPublisher(dto.getPublisher());
        exemplary.setMaximumDurationReservation(dto.getMaximumDurationReservation());

        return exemplary;
    }

    public static ExemplaryDTO exemplary2DTO(Exemplary exemplary) {
        ExemplaryDTO exemplaryDTO = new ExemplaryDTO();
        exemplaryDTO.setId(exemplary.getId());
        exemplaryDTO.setPublisher(exemplary.getPublisher());
        exemplaryDTO.setMaximumDurationReservation(exemplary.getMaximumDurationReservation());
        exemplaryDTO.setBookId(exemplary.getBook().getId());
        return exemplaryDTO;
    }
}

