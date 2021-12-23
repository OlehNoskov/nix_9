package com.project.medical_analize.facade.impl;

import com.project.medical_analize.facade.RegistrationFacade;
import com.project.medical_analize.persistence.entity.user.Personal;
import com.project.medical_analize.service.PersonalCrudService;
import com.project.medical_analize.web.dto.request.register.AuthDto;

import org.springframework.stereotype.Service;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final PersonalCrudService personalService;

    public RegistrationFacadeImpl(PersonalCrudService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(AuthDto dto) {
        Personal personal = new Personal();
            personal.setEmail(dto.getEmail());
            personal.setPassword(dto.getPassword());
            personalService.create(personal);
    }
}