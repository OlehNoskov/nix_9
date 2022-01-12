package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;

public class FeedbackResponseDto extends ResponseDto{

    private String feedback;
    private String patient;

    public FeedbackResponseDto(Feedback feedback) {
        setId(feedback.getId());
        setCreated(feedback.getCreated());
        this.feedback = feedback.getFeedback();
        this.patient = feedback.getNamePatient();
    }
}