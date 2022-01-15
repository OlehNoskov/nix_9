package com.project.medicalanalize.web.dto.request;

import lombok.Getter;
import lombok.Setter;

public class CheckUpRequestDto extends OrderRequestDto{

    @Getter
    @Setter
    private String complaints;
}