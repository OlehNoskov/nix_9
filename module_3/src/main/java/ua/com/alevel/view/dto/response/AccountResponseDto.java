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
    private String balance;

    public AccountResponseDto() {}

    public AccountResponseDto(Account account) {
        this();
        setId(account.getId());
        setCreated(account.getCreated());
        setUpdated(account.getUpdated());
        this.userId = account.getUserId();
        this.accountNumbers = account.getAccountNumbers();
        this.balance = transformMoney(account.getBalance());
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
            money = prepareForView[0] + " hryvnia. 00 kop.";
        }
        System.out.println(findBal);
        return money;
    }
}