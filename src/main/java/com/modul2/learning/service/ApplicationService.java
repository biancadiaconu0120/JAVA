package com.modul2.learning.service;

import com.modul2.learning.entities.Application;
import com.modul2.learning.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    // Create an application without assigning it to a user
    public Application createAppWithoutUser(Application application) {
        return applicationRepository.save(application);
    }
}
