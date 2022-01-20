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

    private String fullNamePatient;

    public FeedbackResponseDto(Feedback feedback) {
        super();
        setId(feedback.getId());
        setCreated(feedback.getCreated());
        this.feedback = feedback.getFeedback();
        this.patientEntity = feedback.getPatient();
    }

    public String getNamePatient() {
        if (patientEntity.getFirstName() == null) {
            return " ";
        }
        return patientEntity.getFirstName();
    }

    public String getFullNamePatient() {
        if (patientEntity.getFirstName() == null && patientEntity.getLastName() == null) {
            return " ";
        }
        return patientEntity.getFirstName() + " " + patientEntity.getLastName();
    }
}