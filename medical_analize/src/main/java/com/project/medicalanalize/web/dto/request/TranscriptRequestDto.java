package com.project.medicalanalize.web.dto.request;

import com.project.medicalanalize.persistence.type.ProphylacticDoses;

import lombok.Getter;
import lombok.Setter;

public class TranscriptRequestDto extends OrderRequestDto {

    @Getter
    @Setter
    private ProphylacticDoses prophylactic_doses_type;

    @Setter
    private Integer price;

    @Getter
    @Setter
    private String complaints;

    public Integer getPrice() {
        return 6;
    }
}