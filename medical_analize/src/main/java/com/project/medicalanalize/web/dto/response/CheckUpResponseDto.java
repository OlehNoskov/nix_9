package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.CheckUp;

import lombok.Getter;

import java.util.Date;

public class CheckUpResponseDto extends OrderResponseDto {

    @Getter
    private Date created;

    @Getter
    private Date updated;

    @Getter
    private Integer price;

    @Getter
    private String complaints;

    public CheckUpResponseDto(CheckUp checkUp) {
        super(checkUp);
        this.created = checkUp.getCreated();
        this.updated = checkUp.getUpdated();
        this.price = CheckUp.getPrice();
        this.complaints = checkUp.getComplaints();
    }
}