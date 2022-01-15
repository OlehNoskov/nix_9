package com.project.medicalanalize.web.dto.request;

import lombok.Getter;
import lombok.Setter;

public class ConsultationRequestDto extends OrderRequestDto {

    @Getter
    @Setter
    private String complaints;
    @Getter
    @Setter
    private String file;
}