package com.project.medicalanalize.web.dto.request;

public class ConsultationRequestDto extends OrderRequestDto {

    private String complaints;
    private String file;

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}