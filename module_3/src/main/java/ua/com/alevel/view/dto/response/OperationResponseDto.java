package ua.com.alevel.view.dto.response;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.persistence.entity.Operation;

public class OperationResponseDto extends ResponseDto{

    @Getter
    @Setter
    private String categoryName;

    @Getter
    @Setter
    private boolean categoryIncomeExpense;

    public OperationResponseDto() {}

    public OperationResponseDto(Operation operation) {
        this();
        setId(operation.getId());
        setCreated(operation.getCreated());
        setUpdated(operation.getUpdated());
        this.categoryName = operation.getCategoryName();
        this.categoryIncomeExpense = operation.isCategoryIncomeExpense();
    }
}