package ua.com.alevel.persistence.entity;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

public class AccountFile {

    @Getter
    private String operationDate;
    @Getter
    private String transactionSum;
    @Getter
    private String categoryName;
    @Getter
    private String incomeExpense;

    public AccountFile(Date operationDate, BigDecimal transactionSum,
                       String categoryName, boolean incomeExpense) {
        this.operationDate = operationDate.toString();
        this.transactionSum = transformMoney(transactionSum);
        this.categoryName = categoryName;

        if (incomeExpense) {
            this.incomeExpense = "Income";
        } else {
            this.incomeExpense = "Consumption";
        }
    }

    private String transformMoney(BigDecimal balance) {
        String money = "";
        BigDecimal findBal = balance.divide(new BigDecimal("100"));
        String[] prepareForView = findBal.toString().split("\\.");
        if (prepareForView.length == 2) {
            if (prepareForView[1].length() == 1) {
                prepareForView[1] = prepareForView[1] + "0";
            }
            money = prepareForView[0] + " hryvnia. " + prepareForView[1] + " cop.";
        } else {
            money = prepareForView[0] + " hryvnia. 00 cop.";
        }
        return money;
    }
}