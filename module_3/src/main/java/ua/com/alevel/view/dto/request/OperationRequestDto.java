package ua.com.alevel.view.dto.request;

public class OperationRequestDto extends RequestDto{

    private String operationName;
    private boolean categoryIncomeExpense;

    public boolean isCategoryIncomeExpense() {
        return categoryIncomeExpense;
    }

    public void setCategoryIncomeExpense(boolean categoryIncomeExpense) {
        this.categoryIncomeExpense = categoryIncomeExpense;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}