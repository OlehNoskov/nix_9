package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.dao.OperationDao;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.service.TransactionService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;
    private final UserDao userDao;
    private final AccountDao accountDao;
    private final OperationDao operationDao;

    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    public TransactionServiceImpl(TransactionDao transactionDao, UserDao userDao, AccountDao accountDao, OperationDao operationDao) {
        this.transactionDao = transactionDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.operationDao = operationDao;
    }

    private Transaction paymentProcessing (Boolean incomeExpense, Transaction entity, Account account) {
        BigDecimal payment;
        BigDecimal factor = new BigDecimal("100");
        if(entity.getTransactionSum() == null) {
            LOGGER_WARN.warn("An attempt to conduct a transaction with zero or negative cash flow!");
            throw new RuntimeException("It is impossible to carry out operations with zero or negative cash turnover!");
        }
        BigDecimal enteredSum = entity.getTransactionSum();
        if(enteredSum.compareTo(new BigDecimal("0")) <= 0 ) {
            LOGGER_WARN.warn("An attempt to conduct a transaction with zero or negative cash flow!");
            throw new RuntimeException("It is impossible to carry out operations with zero or negative cash turnover!");
        }

        String[] calcTransactionSum = enteredSum.toString().split(",");

        if (calcTransactionSum.length == 2) {
            BigDecimal uah = new BigDecimal(calcTransactionSum[0]);
            BigDecimal penny = new BigDecimal(calcTransactionSum[1]);
            payment = uah.multiply(factor);
            payment = payment.add(penny);
        } else {
            BigDecimal uah = new BigDecimal(calcTransactionSum[0]);
            payment = uah.multiply(factor);
        }

        if (incomeExpense) {
            account.setBalance(account.getBalance().add(payment));
            accountDao.update(account);
            entity.setTransactionSum(payment);
        } else {
            if(account.getBalance().compareTo(payment) < 0) {
                LOGGER_WARN.warn("An attempt to carry out an operation with insufficient CLV. funds!");
                throw new RuntimeException("The overdraft feature is disabled, you can't afford it!");
            }
            else {
                account.setBalance(account.getBalance().subtract(payment));
                accountDao.update(account);
                entity.setTransactionSum(payment);
            }
        }
        return entity;
    }

    @Override
    public void create(Transaction entity) {
        Operation operation = operationDao.findById(entity.getCategoryId());
        Account account = accountDao.findById(entity.getAccountId());
        boolean incomeExpense = operation.isCategoryIncomeExpense();
        paymentProcessing(incomeExpense, entity, account);

        entity.setUserId(account.getUserId());
        entity.setAccountNumbers(account.getAccountNumbers());
        entity.setCategoryName(operation.getCategoryName());
        entity.setCategoryIncomeExpense(incomeExpense);
        entity.setUserName(userDao.findById(entity.getUserId()).getFirstName());
        transactionDao.create(entity);
    }

    @Override
    public Transaction findById(Long id) {
        return null;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }

    @Override
    public List<Transaction> findTransactionsByUserId(Long userId) {
        return transactionDao.findTransactionsByUserId(userId);
    }

    @Override
    public List<Transaction> findTransactionsByAccountId(Long accountId) {
        return transactionDao.findTransactionsByAccountId(accountId);
    }

    @Override
    public void update(Transaction entity) {}

    @Override
    public void delete(Long id) {}
}