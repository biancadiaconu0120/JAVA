package com.modul2.learning.mapper;

import com.modul2.learning.dto.LibrarianDTO;
import com.modul2.learning.entities.Librarian;
import com.modul2.learning.utils.PasswordUtils;

public class LibrarianMapper {
    public static Librarian librarianDTO2Librarian(LibrarianDTO dto) {
        Librarian librarian = new Librarian();
        librarian.setName(dto.getName());
        librarian.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            librarian.setPassword(PasswordUtils.hashPassword(dto.getPassword().trim()));
        }
        librarian.setAccountVerified(false);
        if (dto.getLibrary() != null) {
            librarian.setLibrary(LibraryMapper.libraryDTO2Library(dto.getLibrary()));
        }
        return librarian;
    }

    public static LibrarianDTO librarian2LibrarianDTO(Librarian librarian) {
        LibrarianDTO dto = new LibrarianDTO();
        dto.setId(librarian.getId());
        dto.setName(librarian.getName());
        dto.setEmail(librarian.getEmail());
        dto.setAccountVerified(librarian.isAccountVerified());
        dto.setPassword(null); // do not expose password
        if (librarian.getLibrary() != null) {
            dto.setLibrary(LibraryMapper.library2LibraryDTO(librarian.getLibrary()));
        }
        return dto;
    }
}
