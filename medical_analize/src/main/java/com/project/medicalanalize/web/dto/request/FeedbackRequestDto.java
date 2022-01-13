package com.project.medicalanalize.web.dto.request;

import com.project.medicalanalize.persistence.entity.user.Patient;

public class FeedbackRequestDto extends RequestDto {

    private String feedback;
    private Long patientId;
    private Patient patient;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}