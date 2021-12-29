package ua.com.alevel.persistence.entity;

import ua.com.alevel.view.dto.request.AccountStatementRequestDto;

public class AccountStatement {

    private Long id;
    private String dateFrom;
    private String dateTo;

    public AccountStatement(AccountStatementRequestDto dto) {
        this.id = dto.getId();
        this.dateFrom = dto.getDateFrom();
        this.dateTo = dto.getDateTo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}