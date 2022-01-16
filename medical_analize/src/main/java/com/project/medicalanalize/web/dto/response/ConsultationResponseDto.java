package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;

import lombok.Getter;

import java.util.Date;

public class ConsultationResponseDto extends OrderResponseDto {

    @Getter
    private Date created;

    @Getter
    private Integer price;

    @Getter
    private String complaints;

    public ConsultationResponseDto(ConsultationOrder consultationOrder) {
        super(consultationOrder);
        this.created = consultationOrder.getCreated();
        this.price = consultationOrder.getPrice();
        this.complaints = consultationOrder.getComplaints();
    }
}