package com.modul2.learning.mapper;

import com.modul2.learning.dto.LibraryDTO;
import com.modul2.learning.entities.Library;

public class LibraryMapper {
    public static Library libraryDTO2Library(LibraryDTO dto) {
        Library library = new Library();
        library.setName(dto.getName());
        library.setAddress(dto.getAddress());
        library.setPhoneNumber(dto.getPhoneNumber());
        return library;
    }

    public static LibraryDTO library2LibraryDTO(Library library) {
        LibraryDTO dto = new LibraryDTO();
        dto.setId(library.getId());
        dto.setName(library.getName());
        dto.setAddress(library.getAddress());
        dto.setPhoneNumber(library.getPhoneNumber());
        return dto;
    }
}
