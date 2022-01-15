package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.ComprehensiveConsultationOrder;

import lombok.Getter;

import java.util.Date;

public class ConsultationResponseDto extends OrderResponseDto {

    @Getter
    private Date created;

    @Getter
    private Integer price;

    @Getter
    private String complaints;

    @Getter
    private String file;

    public ConsultationResponseDto(ComprehensiveConsultationOrder consultationOrder) {
        super(consultationOrder);
        this.created = consultationOrder.getCreated();
        this.price = consultationOrder.getPrice();
        this.complaints = consultationOrder.getComplaints();
        this.file = consultationOrder.getFile();
    }
}