package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Operation;

public class OperationResponseDto extends ResponseDto{
    private String categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCategoryIncomeExpense() {
        return categoryIncomeExpense;
    }

    public void setCategoryIncomeExpense(boolean categoryIncomeExpense) {
        this.categoryIncomeExpense = categoryIncomeExpense;
    }
}