package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.service.TransactionService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.view.dto.request.TransactionRequestDto;
import ua.com.alevel.view.dto.response.TransactionResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionFacadeImpl implements TransactionFacade {

    private final TransactionService transactionService;

    public TransactionFacadeImpl(TransactionService categoryService,
                                 UserService userService,
                                 AccountService accountService,
                                 OperationService operationService) {
        this.transactionService = categoryService;
    }

    @Override
    public void create(TransactionRequestDto transactionRequestDto) {
    }

    @Override
    public void create(TransactionRequestDto req, Long operationId) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(req.getAccountId());
        transaction.setTransactionSum(req.getTransactionSum());
        transaction.setCategoryId(operationId);
        transactionService.create(transaction);
    }

    @Override
    public List<TransactionResponseDto> findTransactionsByUserId(Long userId) {
        List<Transaction> transactionList = transactionService.findTransactionsByUserId(userId);
        List<TransactionResponseDto> allTransactions = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            allTransactions.add(new TransactionResponseDto(transaction));
        }
        return allTransactions;
    }

    @Override
    public List<TransactionResponseDto> findTransactionsByAccountId(Long accountId) {
        List<Transaction> transactionList = transactionService.findTransactionsByAccountId(accountId);
        List<TransactionResponseDto> allTransactions = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            allTransactions.add(new TransactionResponseDto(transaction));
        }
        return allTransactions;
    }

    @Override
    public void update(TransactionRequestDto transactionRequestDto) {
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public TransactionResponseDto findById(Long id) {
        return null;
    }

    @Override
    public List<TransactionResponseDto> findAll() {
        List<Transaction> transactionList = transactionService.findAll();
        List<TransactionResponseDto> allTransactions = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            allTransactions.add(new TransactionResponseDto(transaction));
        }
        return allTransactions;
    }
}