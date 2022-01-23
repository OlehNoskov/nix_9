package com.project.medicalanalize.web.dto.request;

import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;

import lombok.Getter;
import lombok.Setter;

public class ConsultationRequestDto extends OrderRequestDto {

    @Getter
    @Setter
    private String complaints;

    @Getter
    @Setter
    private String medicines;

    @Getter
    @Setter
    private String profileDoctor;

    @Getter
    @Setter
    private String instrumentalResearch;

    @Getter
    @Setter
    private String nutritionalAdvice;

    public Integer getPrice() {
        return ConsultationOrder.getPrice();
    }
}