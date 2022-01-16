package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.facade.impl.TranscriptFacadeImpl;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;

import lombok.Getter;

import java.util.Date;

public class TranscriptResponseDto extends OrderResponseDto {

    @Getter
    private Integer price;

    @Getter
    private Date created;

    private Integer countAllTranscript;

    public TranscriptResponseDto(TranscriptOrder transcript) {
        super(transcript);
        this.created = transcript.getCreated();
        this.price = transcript.getPrice();
    }

    public Integer getCountAllTranscript() {
        return TranscriptFacadeImpl.countTranscript;
    }
}