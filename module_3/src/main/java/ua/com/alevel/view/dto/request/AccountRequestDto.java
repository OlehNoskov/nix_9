package ua.com.alevel.view.dto.request;

import lombok.Getter;
import lombok.Setter;

public class AccountRequestDto extends RequestDto {

    @Getter
    @Setter
    private String categoryName;

    @Getter
    @Setter
    private boolean categoryIncomeExpense;
}