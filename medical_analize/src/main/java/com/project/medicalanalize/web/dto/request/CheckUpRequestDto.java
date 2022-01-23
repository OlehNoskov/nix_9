package com.project.medicalanalize.web.dto.request;

import com.project.medicalanalize.persistence.entity.order.CheckUp;

import lombok.Getter;
import lombok.Setter;

public class CheckUpRequestDto extends OrderRequestDto{

    @Getter
    @Setter
    private String complaints;

    public Integer getPrice() {
        return CheckUp.getPrice();
    }
}