package com.project.medicalanalize.web.dto.request;

import lombok.Getter;
import lombok.Setter;

public class TranscriptRequestDto extends OrderRequestDto {

    @Getter
    @Setter
    private String file;
}