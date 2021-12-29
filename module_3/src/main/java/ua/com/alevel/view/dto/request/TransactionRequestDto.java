package ua.com.alevel.view.dto.request;

import java.math.BigDecimal;

public class TransactionRequestDto extends RequestDto{

    private BigDecimal transactionSum;
    private Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(BigDecimal transactionSum) {
        this.transactionSum = transactionSum;
    }
}