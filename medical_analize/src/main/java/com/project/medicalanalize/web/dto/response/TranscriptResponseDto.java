package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;

public class TranscriptResponseDto extends OrderResponseDto {

    private Integer price;
    private String file;

    public TranscriptResponseDto(TranscriptOrder transcript) {
        super();
        this.price = transcript.getPrice();
        this.file = transcript.getFile();
    }

    public Integer getPrice() {
        return price;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}   