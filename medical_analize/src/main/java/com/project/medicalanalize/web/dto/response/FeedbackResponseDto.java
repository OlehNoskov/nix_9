package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.entity.user.Patient;

public class FeedbackResponseDto extends ResponseDto{

    private String feedback;
    private String namePatient;
    Patient patientEntity;

    public FeedbackResponseDto() {
    }

    public FeedbackResponseDto(Feedback feedback) {
        setId(feedback.getId());
        setCreated(feedback.getCreated());
        setUpdated(feedback.getUpdated());
        this.feedback = feedback.getFeedback();
        this.namePatient = feedback.getNamePatient();
        this.patientEntity = feedback.getPatient();
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public Patient getPatientEntity() {
        return patientEntity;
    }

    public void setPatientEntity(Patient patientEntity) {
        this.patientEntity = patientEntity;
    }
}