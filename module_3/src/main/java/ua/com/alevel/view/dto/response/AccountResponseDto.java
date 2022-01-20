package ua.com.alevel.view.dto.response;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.persistence.entity.Account;

import java.math.BigDecimal;

public class AccountResponseDto extends ResponseDto{

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private String accountNumbers;

    @Getter
    @Setter
    private BigDecimal balance;

    public AccountResponseDto() {}

    public AccountResponseDto(Account account) {
        this();
        setId(account.getId());
        setCreated(account.getCreated());
        setUpdated(account.getUpdated());
        this.userId = account.getUserId();
        this.accountNumbers = account.getAccountNumbers();
        this.balance = account.getBalance();
    }
}