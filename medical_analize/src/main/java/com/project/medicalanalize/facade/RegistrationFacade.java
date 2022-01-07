package com.project.medicalanalize.facade;

import com.project.medicalanalize.web.dto.request.register.AuthDto;

public interface RegistrationFacade {
    void registration(AuthDto dto);
}