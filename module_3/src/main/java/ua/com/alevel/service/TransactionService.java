package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Transaction;

import java.util.List;

public interface TransactionService extends BaseService<Transaction>{

    List<Transaction> findTransactionsByUserId(Long userId);
    List<Transaction> findTransactionsByAccountId(Long accountId);
}