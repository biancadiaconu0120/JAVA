package com.modul2.learning.mapper;

import com.modul2.learning.dto.ApplicationDTO;
import com.modul2.learning.entities.Application;

public class ApplicationMapper {
    public static Application applicationDTO2Application(ApplicationDTO applicationDTO) {
        Application application = new Application();
        application.setName(applicationDTO.getName());
        return application;
    }

    public static ApplicationDTO application2ApplicationDTO(Application application) {
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(application.getId());
        applicationDTO.setName(application.getName());
        return applicationDTO;
    }
}