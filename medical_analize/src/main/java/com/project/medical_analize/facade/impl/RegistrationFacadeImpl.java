package com.project.medical_analize.facade.impl;

import com.project.medical_analize.facade.RegistrationFacade;
import com.project.medical_analize.persistence.entity.user.Doctor;
import com.project.medical_analize.persistence.entity.user.Patient;
import com.project.medical_analize.service.UserCrudService;
import com.project.medical_analize.web.dto.request.register.AuthDto;

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
            case "DOCTOR" : {
                Doctor doctor = new Doctor();
                doctor.setPassword(dto.getPassword());
                doctor.setEmail(dto.getEmail());
                personalService.create(doctor);
            } break;
            case "PATIENT" : {
                Patient patient = new Patient();
                patient.setPassword(dto.getPassword());
                patient.setEmail(dto.getEmail());
                personalService.create(patient);
            }
        }
    }
}