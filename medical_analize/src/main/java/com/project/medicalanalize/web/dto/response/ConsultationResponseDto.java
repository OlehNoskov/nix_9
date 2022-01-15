package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.ComprehensiveConsultationOrder;
import lombok.Getter;

public class ConsultationResponseDto extends OrderResponseDto {

    @Getter
    private Integer price;
    @Getter
    private String complaints;
    @Getter
    private String file;

    public ConsultationResponseDto(ComprehensiveConsultationOrder consultationOrder) {
        super();
        this.price = consultationOrder.getPrice();
        this.complaints = consultationOrder.getComplaints();
        this.file = consultationOrder.getFile();
    }
}