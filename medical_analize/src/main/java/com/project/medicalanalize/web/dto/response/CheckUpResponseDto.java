package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.CheckUp;
import lombok.Getter;

public class CheckUpResponseDto extends OrderResponseDto {

    @Getter
    private Integer price;
    @Getter
    private String complaints;

    public CheckUpResponseDto(CheckUp checkUp) {
        super();
        this.price = checkUp.getPrice();
        this.complaints = checkUp.getComplaints();
    }
}