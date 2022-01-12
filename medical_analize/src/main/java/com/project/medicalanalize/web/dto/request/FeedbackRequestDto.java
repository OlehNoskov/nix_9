package com.project.medicalanalize.web.dto.request;

public class FeedbackRequestDto extends RequestDto{

    private String feedback;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}