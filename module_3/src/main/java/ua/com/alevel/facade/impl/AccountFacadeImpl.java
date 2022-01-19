package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Statement;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.request.AccountStatementRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;

    public AccountFacadeImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void create(Long id) {
        accountService.create(id);
    }

    @Override
    public void update(AccountRequestDto accountRequestDto) {
    }

    @Override
    public void create(AccountRequestDto accountRequestDto) {
    }

    @Override
    public void delete(Long id) {
        accountService.delete(id);
    }

    @Override
    public AccountResponseDto findById(Long id) {
        return new AccountResponseDto(accountService.findById(id));
    }

    @Override
    public List<AccountResponseDto> findAll() {
        List<Account> accountList = accountService.findAll();
        List<AccountResponseDto> userAccounts = new ArrayList<>();

        for (int i = 0; i < accountList.size(); i++) {
            userAccounts.add(new AccountResponseDto(accountList.get(i)));
        }
        return userAccounts;
    }

    @Override
    public List<AccountResponseDto> findByUserId(Long id) {
        List<Account> accountList = accountService.findByUserId(id);
        List<AccountResponseDto> userAccounts = new ArrayList<>();

        for (int i = 0; i < accountList.size(); i++) {
            userAccounts.add(new AccountResponseDto(accountList.get(i)));
        }
        return userAccounts;
    }

    @Override
    public String getAccountStatementFileForDownload(AccountStatementRequestDto dto) {
        return accountService.getAccountStatementFileForDownload(new Statement(dto));
    }
}