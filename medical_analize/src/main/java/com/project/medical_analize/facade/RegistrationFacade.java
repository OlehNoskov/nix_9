package com.project.medical_analize.facade;

import com.project.medical_analize.web.dto.request.register.AuthDto;

public interface RegistrationFacade {
    void registration(AuthDto dto);
}