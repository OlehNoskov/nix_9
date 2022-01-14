package com.project.medicalanalize.web.dto.request;

public class CheckUpRequestDto extends OrderRequestDto{

    private String complaints;

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }
}