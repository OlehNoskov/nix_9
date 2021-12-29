package ua.com.alevel.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AccountForFile {

    private String operationDate;
    private String transactionSum;
    private String categoryName;
    private String incomeExpense;

    public AccountForFile(Date operationDate, BigDecimal transactionSum,
                          String categoryName, boolean incomeExpense) {
        this.operationDate = operationDate.toString();
        this.transactionSum = transformMoney(transactionSum);
        this.categoryName = categoryName;

        if(incomeExpense) {
            this.incomeExpense = "Доход";
        } else {
            this.incomeExpense = "Расход";
        }
    }

    public String getOperationDate() {
        return operationDate;
    }

    public String getTransactionSum() {
        return transactionSum;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getIncomeExpense() {
        return incomeExpense;
    }

    private  String transformMoney (BigDecimal balance) {
        String money = "";
        BigDecimal findBal = balance.divide(new BigDecimal ("100"));
        String [] prepareForView = findBal.toString().split("\\.");
        if(prepareForView.length == 2) {
            if(prepareForView[1].length() == 1) {
                prepareForView[1] = prepareForView[1] + "0";
            }
            money = prepareForView[0] + " hryvnia. " +  prepareForView[1] + " cop.";
        }
        else {
            money = prepareForView[0] + " hryvnia. 00 cop.";
        }
        System.out.println(findBal);
        return money;
    }
}