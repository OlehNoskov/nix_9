package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Account;

import java.math.BigDecimal;

public class AccountResponseDto extends ResponseDto{

    private Long userId;
    private String accountNumbers;
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
            money = prepareForView[0] + " грн. " +  prepareForView[1] + " коп.";
        }
        else {
            money = prepareForView[0] + " грн. 00 коп.";
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

    public String getAccountNumbers() {
        return accountNumbers;
    }

    public void setAccountNumbers(String accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}