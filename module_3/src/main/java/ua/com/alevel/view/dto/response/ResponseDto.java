package ua.com.alevel.view.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public abstract class ResponseDto {

    private Long id;
    private Date created;
    private Date updated;
}