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

    @Getter
    private String medicines;

    @Getter
    private String profileDoctor;

    @Getter
    private String instrumentalResearch;

    @Getter
    private String nutritionalAdvice;

    public ConsultationResponseDto(ConsultationOrder consultationOrder) {
        super(consultationOrder);
        this.created = consultationOrder.getCreated();
        this.price = ConsultationOrder.getPrice();
        this.complaints = consultationOrder.getComplaints();
        this.medicines = consultationOrder.getMedicines();
        this.profileDoctor = consultationOrder.getProfileDoctor();
        this.instrumentalResearch = consultationOrder.getInstrumentalResearch();
        this.nutritionalAdvice = consultationOrder.getNutritionalAdvice();
    }
}