package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.entity.user.Patient;
import lombok.Getter;
import lombok.Setter;

public class FeedbackResponseDto extends ResponseDto{

    @Getter
    @Setter
    private String feedback;
    @Getter
    @Setter
    private String namePatient;
    @Getter
    @Setter
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
}