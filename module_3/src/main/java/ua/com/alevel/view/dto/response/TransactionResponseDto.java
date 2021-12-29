package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Transaction;

import java.math.BigDecimal;

public class TransactionResponseDto extends ResponseDto{

    private Long userId;
    private Long accountId;
    private Long categoryId;
    private String userName;
    private String accountNumbers;
    private String categoryName;
    private String transactionSum;
    private boolean categoryIncomeExpense;

    public TransactionResponseDto() {
    }

    public TransactionResponseDto(Transaction transaction) {
        this();
        setId(transaction.getId());
        setCreated(transaction.getCreated());
        setUpdated(transaction.getUpdated());
        this.userId = transaction.getUserId();
        this.accountId = transaction.getAccountId();
        this.categoryId = transaction.getCategoryId();
        this.transactionSum = String.valueOf(transaction.getTransactionSum());
        this.transactionSum = transformMoney(transaction.getTransactionSum());
        this.userName = transaction.getUserName();
        this.accountNumbers = transaction.getAccountNumbers();
        this.categoryName = transaction.getCategoryName();
        this.categoryIncomeExpense = transaction.isCategoryIncomeExpense();
    }

    private String transformMoney (BigDecimal balance) {
        String money = "";
        BigDecimal findBal = balance.divide(new BigDecimal ("100"));
        String [] prepareForView = findBal.toString().split("\\.");
        if(prepareForView.length == 2) {
            if(prepareForView[1].length() == 1) {
                prepareForView[1] = prepareForView[1] + "0";
            }
            money = prepareForView[0] + " hryvnia. " +  prepareForView[1] + " kop.";
        }
        else {
            money = prepareForView[0] + "hryvnia. 00 kop.";
        }
        System.out.println(findBal);
        return money;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountNumbers() {
        return accountNumbers;
    }

    public void setAccountNumbers(String accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(String transactionSum) {
        this.transactionSum = transactionSum;
    }

    public boolean isCategoryIncomeExpense() {
        return categoryIncomeExpense;
    }

    public void setCategoryIncomeExpense(boolean categoryIncomeExpense) {
        this.categoryIncomeExpense = categoryIncomeExpense;
    }
}