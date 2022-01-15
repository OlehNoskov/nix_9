package com.project.medicalanalize.web.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public abstract class ResponseDto {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private Date created;

    @Getter
    @Setter
    private Date updated;

    @Getter
    @Setter
    private boolean visible;
}