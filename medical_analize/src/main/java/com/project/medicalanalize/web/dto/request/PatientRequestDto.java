package com.project.medicalanalize.web.dto.request;

import lombok.Getter;
import lombok.Setter;

public class PatientRequestDto extends UserRequestDto {
    @Getter
    @Setter
    private Integer height;

    @Getter
    @Setter
    private Integer weight;
}