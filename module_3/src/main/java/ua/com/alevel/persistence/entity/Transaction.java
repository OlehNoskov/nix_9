package ua.com.alevel.persistence.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transactions")
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "transaction_sum")
    private BigDecimal transactionSum;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "account_numbers")
    private String accountNumbers;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_income_expense")
    private Boolean categoryIncomeExpense;

    public Transaction(Long userId, Long accountId, Long categoryId, BigDecimal transactionSum, String userName, String accountNumbers, String categoryName, Boolean categoryIncomeExpense) {
        this.userId = userId;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.transactionSum = transactionSum;
        this.userName = userName;
        this.accountNumbers = accountNumbers;
        this.categoryName = categoryName;
        this.categoryIncomeExpense = categoryIncomeExpense;
    }

    public Transaction() {}

    public Boolean isCategoryIncomeExpense() {
        return categoryIncomeExpense;
    }
}