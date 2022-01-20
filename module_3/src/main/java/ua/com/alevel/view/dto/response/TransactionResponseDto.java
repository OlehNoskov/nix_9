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
    private BigDecimal transactionSum;
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
        this.transactionSum = transaction.getTransactionSum();
        this.userName = transaction.getUserName();
        this.accountNumbers = transaction.getAccountNumbers();
        this.categoryName = transaction.getCategoryName();
        this.categoryIncomeExpense = transaction.isCategoryIncomeExpense();
    }
}