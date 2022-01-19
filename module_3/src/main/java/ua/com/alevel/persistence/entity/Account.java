package ua.com.alevel.persistence.entity;

import groovy.transform.EqualsAndHashCode;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

    @Column(name = "user_id")
    @Getter
    @Setter
    private Long userId;

    @Column(name = "account_numbers")
    @Getter
    @Setter
    private String accountNumbers;

    @Column(name = "account_balance")
    @Getter
    @Setter
    private BigDecimal balance;

    public Account() {
    }

    public Account(Long userId, String accountNumbers, BigDecimal balance) {
        this.userId = userId;
        this.accountNumbers = accountNumbers;
        this.balance = balance;
    }
}