package com.project.medicalanalize.web.dto.request.register;

import lombok.Getter;
import lombok.Setter;

public class AuthDto {

    @Getter
    @Setter
    private String role;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String passwordConfirm;
}