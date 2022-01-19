package ua.com.alevel.view.dto.response;

import lombok.Data;
import ua.com.alevel.persistence.entity.Transaction;

import java.math.BigDecimal;

@Data
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
}