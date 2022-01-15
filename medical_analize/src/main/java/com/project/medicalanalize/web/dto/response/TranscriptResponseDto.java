package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import lombok.Getter;

public class TranscriptResponseDto extends OrderResponseDto {

    @Getter
    private Integer price;
    @Getter
    private String file;

    public TranscriptResponseDto(TranscriptOrder transcript) {
        super();
        this.price = transcript.getPrice();
        this.file = transcript.getFile();
    }
}   