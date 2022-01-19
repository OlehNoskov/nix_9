package ua.com.alevel.view.dto.request;

import lombok.Getter;
import lombok.Setter;

public class AccountStatementRequestDto extends RequestDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String dateFrom;

    @Getter
    @Setter
    private String dateTo;

    public AccountStatementRequestDto(Long id) {
        this.id = id;
    }
}