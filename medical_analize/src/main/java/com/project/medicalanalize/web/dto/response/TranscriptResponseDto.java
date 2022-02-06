package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.type.ProphylacticDoses;

import lombok.Getter;

import java.util.Date;

public class TranscriptResponseDto extends OrderResponseDto {

    @Getter
    private Integer price;

    @Getter
    private String complaints;

    @Getter
    private Date created;

    @Getter
    private Date updated;

    @Getter
    ProphylacticDoses prophylacticDoses;

    public TranscriptResponseDto(TranscriptOrder transcript) {
        super(transcript);
        this.created = transcript.getCreated();
        this.updated = transcript.getUpdated();
        this.complaints = transcript.getComplaints();
        this.price = TranscriptOrder.getPrice();
        this.prophylacticDoses = transcript.getProphylacticDoses();
    }
}