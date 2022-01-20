package ua.com.alevel.persistence.entity;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

public class AccountsData {

    @Getter
    private String operationDate;
    @Getter
    private BigDecimal transactionSum;
    @Getter
    private String categoryName;
    @Getter
    private String incomeExpense;

    public AccountsData(Date operationDate, BigDecimal transactionSum,
                        String categoryName, boolean incomeExpense) {
        this.operationDate = operationDate.toString();
        this.transactionSum = transactionSum;
        this.categoryName = categoryName;

        if (incomeExpense) {
            this.incomeExpense = "Income";
        } else {
            this.incomeExpense = "Consumption";
        }
    }
}