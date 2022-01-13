package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.ComprehensiveConsultationOrder;

public class ConsultationResponseDto extends OrderResponseDto {

    private Integer price;
    private String complaints;
    private String file;

    public ConsultationResponseDto(ComprehensiveConsultationOrder consultationOrder) {
        super();
        this.price = consultationOrder.getPrice();
        this.complaints = consultationOrder.getComplaints();
        this.file = consultationOrder.getFile();
    }

    public Integer getPrice() {
        return price;
    }

    public String getComplaints() {
        return complaints;
    }

    public String getFile() {
        return file;
    }
}