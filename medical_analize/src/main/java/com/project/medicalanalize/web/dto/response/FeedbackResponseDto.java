package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.entity.user.Patient;

import lombok.Getter;

public class FeedbackResponseDto extends ResponseDto {

    @Getter
    private String feedback;

    @Getter
    Patient patientEntity;

    private String namePatient;

    public FeedbackResponseDto(Feedback feedback) {
        super();
        setId(feedback.getId());
        System.out.println(feedback.getId());
        this.feedback = feedback.getFeedback();
        this.patientEntity = feedback.getPatient();
    }

    public String getNamePatient() {
        return patientEntity.getFirstName();
    }
}