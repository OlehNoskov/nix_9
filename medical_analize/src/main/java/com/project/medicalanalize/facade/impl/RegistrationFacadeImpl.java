package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.RegistrationFacade;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.service.UserCrudService;
import com.project.medicalanalize.web.dto.request.register.AuthDto;

import org.springframework.stereotype.Service;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final UserCrudService personalService;

    public RegistrationFacadeImpl(UserCrudService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(AuthDto dto) {
        switch (dto.getRole()) {
            case "DOCTOR" -> {
                Doctor doctor = new Doctor();
                doctor.setPassword(dto.getPassword());
                doctor.setEmail(dto.getEmail());
                personalService.create(doctor);
            }
            case "PATIENT" -> {
                Patient patient = new Patient();
                patient.setPassword(dto.getPassword());
                patient.setEmail(dto.getEmail());
                personalService.create(patient);
            }
        }
    }
}