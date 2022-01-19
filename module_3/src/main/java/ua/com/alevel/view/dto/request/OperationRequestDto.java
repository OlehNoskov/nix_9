package ua.com.alevel.view.dto.request;

import lombok.Getter;
import lombok.Setter;

public class OperationRequestDto extends RequestDto{


    @Getter
    @Setter
    private String operationName;

    @Getter
    @Setter
    private boolean categoryIncomeExpense;
}