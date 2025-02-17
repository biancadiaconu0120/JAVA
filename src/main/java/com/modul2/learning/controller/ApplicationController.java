package com.modul2.learning.controller;

import com.modul2.learning.dto.ApplicationDTO;
import com.modul2.learning.entities.Application;
import com.modul2.learning.mapper.ApplicationMapper;
import com.modul2.learning.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")  // Ensure this matches your Postman request
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping()
    public ResponseEntity<?> createApplication(@RequestBody ApplicationDTO applicationDTO) {
        Application application = ApplicationMapper.applicationDTO2Application(applicationDTO);
        Application createdApplication = applicationService.createAppWithoutUser(application);
        return ResponseEntity.ok(ApplicationMapper.application2ApplicationDTO(createdApplication));
    }
}

