package com.project.medicalanalize.web.dto.request;

import com.project.medicalanalize.persistence.entity.user.Patient;

import lombok.Getter;
import lombok.Setter;

public class FeedbackRequestDto extends RequestDto {

    @Getter
    @Setter
    private String textFeedback;

    @Getter
    @Setter
    private Long patientId;

    @Getter
    @Setter
    private Patient patient;
}