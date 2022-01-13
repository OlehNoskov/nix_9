package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.CheckUp;

public class CheckUpResponseDto extends OrderResponseDto {

    private Integer price;
    private String complaints;

    public CheckUpResponseDto(CheckUp checkUp) {
        super();
        this.price = checkUp.getPrice();
        this.complaints = checkUp.getComplaints();
    }

    public Integer getPrice() {
        return price;
    }

    public String getComplaints() {
        return complaints;
    }
}