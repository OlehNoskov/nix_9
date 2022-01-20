package com.project.medicalanalize.web.dto.request;

import lombok.Getter;
import lombok.Setter;

public class CheckUpRequestDto extends OrderRequestDto{

//    @Getter
//    private Integer price;

    @Getter
    @Setter
    private String complaints;
}