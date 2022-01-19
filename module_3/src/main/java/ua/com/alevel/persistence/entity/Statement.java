package ua.com.alevel.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.view.dto.request.AccountStatementRequestDto;

public class Statement {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String beginDate;

    @Getter
    @Setter
    private String endDate;

    public Statement(AccountStatementRequestDto dto) {
        this.id = dto.getId();
        this.beginDate = dto.getDateFrom();
        this.endDate = dto.getDateTo();
    }
}