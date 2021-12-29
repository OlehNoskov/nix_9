package ua.com.alevel.persistence.entity;

import groovy.transform.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "account_numbers")
    private String accountNumbers;

    @Column(name = "account_balance")
    private BigDecimal balance;

    public Account() {
    }

    public Account(Long userId, String accountNumbers, BigDecimal balance) {
        this.userId = userId;
        this.accountNumbers = accountNumbers;
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountNumbers() {
        return accountNumbers;
    }

    public void setAccountNumbers(String accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}