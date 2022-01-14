package com.project.medicalanalize.web.dto.request;

public class TranscriptRequestDto extends OrderRequestDto {

    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}