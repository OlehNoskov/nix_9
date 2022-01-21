package com.project.medicalanalize.web.dto.request;

import lombok.Getter;
import lombok.Setter;

public class CheckUpRequestDto extends OrderRequestDto{

    @Setter
    private Integer price;

    @Getter
    @Setter
    private String complaints;

    public Integer getPrice() {
        return 8;
    }
}