package com.modul2.learning.controller;

import com.modul2.learning.dto.ExemplaryDTO;
import com.modul2.learning.entities.Exemplary;
import com.modul2.learning.mapper.ExemplaryMapping;
import com.modul2.learning.repository.ExemplaryRepository;
import com.modul2.learning.service.ExemplaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exemplars")
public class ExemplaryController {

    @Autowired
    private ExemplaryService exemplaryService;
    @Autowired
    private ExemplaryRepository exemplaryRepository;

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
    @GetMapping("/book/{bookId}/paginated")
    public ResponseEntity<Page<ExemplaryDTO>> getPaginatedExemplarsByBook(@PathVariable Long bookId, @RequestParam int page, @RequestParam int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction) {

        Page<Exemplary> exemplarsPage = exemplaryService.getPaginatedExemplarsByBook(bookId, page, size, sortBy, direction);
        Page<ExemplaryDTO> exemplaryDTOs = exemplarsPage.map(ExemplaryMapping::exemplary2DTO);

        return ResponseEntity.ok(exemplaryDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExemplary(@PathVariable Long id) {
        exemplaryService.deleteExemplary(id);
        return ResponseEntity.ok("Exemplary deleted successfully");
    }
}
