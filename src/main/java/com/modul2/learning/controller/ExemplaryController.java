package com.modul2.learning.controller;

import com.modul2.learning.dto.ExemplaryDTO;
import com.modul2.learning.entities.Exemplary;
import com.modul2.learning.mapper.ExemplaryMapping;
import com.modul2.learning.service.ExemplaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exemplars")
public class ExemplaryController {

    @Autowired
    private ExemplaryService exemplaryService;

    // Add exemplars to a book
    @PostMapping("/book/{bookId}/create-multiple")
    public ResponseEntity<?> createMultipleExemplars(
            @PathVariable Long bookId,
            @RequestParam int numberOfExemplars,
            @RequestBody ExemplaryDTO exemplaryDTO) {

        Exemplary exemplaryToCreate = ExemplaryMapping.exemplaryDTO2Exemplary(exemplaryDTO);
        List<Exemplary> createdExemplars = exemplaryService.createMultipleExemplars(bookId, numberOfExemplars, exemplaryToCreate);

        List<ExemplaryDTO> exemplaryDTOs = createdExemplars.stream()
                .map(ExemplaryMapping::exemplary2DTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(exemplaryDTOs);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ExemplaryDTO>> getExemplarsByBook(@PathVariable Long bookId) {
        List<Exemplary> exemplars = exemplaryService.getExemplarsByBook(bookId);
        List<ExemplaryDTO> exemplaryDTOs = exemplars.stream()
                .map(ExemplaryMapping::exemplary2DTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(exemplaryDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExemplary(@PathVariable Long id) {
        exemplaryService.deleteExemplary(id);
        return ResponseEntity.ok("Exemplary deleted successfully");
    }
}
