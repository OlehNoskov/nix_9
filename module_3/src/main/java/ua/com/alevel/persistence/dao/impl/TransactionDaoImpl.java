package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.entity.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    private final SessionFactory sessionFactory;
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public TransactionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Transaction entity) {
        LOGGER_INFO.info("Выдан запрос на создание новой Транзакции!");
        try {
            sessionFactory.getCurrentSession().persist(entity);
        }catch (Exception he)/*HibernateException*/ {
            LOGGER_ERROR.error("Не удалось создать новую Транзакцию! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось создать новую Транзакцию! Обратитесь к администратору!");
        }
        LOGGER_INFO.info("Запрос на создание новой Транзакции выполнен!");
    }

    @Override
    public List<Transaction> findAll() {
        LOGGER_INFO.info("Выдан запрос на поиск всех Транзакций");
        List<Transaction> transactionsAll = new LinkedList<>();

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
            Root<Transaction> from = criteriaQuery.from(Transaction.class);
            transactionsAll = session.createQuery(criteriaQuery).getResultList();
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось найти все Транзакции! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось найти все Транзакции!");
        }

        LOGGER_INFO.info("Запрос на поиск всех Транзакций выполнен!");
        return transactionsAll;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Transaction> findTransactionsByUserId(Long userId) {
        LOGGER_INFO.info("Выдан запрос на поиск всех Транзакций по ID = " + userId + " пользователя!");
        try {
            List <Transaction> userTransactions = sessionFactory.getCurrentSession().
                    createQuery("from Transaction tr where tr.userId = :id")
                    .setParameter("id", userId)
                    .getResultList();
            LOGGER_INFO.info("Запрос на поиск всех Транзакций по ID = " + userId + " пользователя выполнен!");
            return userTransactions;
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось найти всех Транзакций по ID = " + userId + ". Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось найти всех Транзакций по ID = " + userId);
        }
    }

    @Override
    public List<Transaction> findTransactionsByAccountId(Long accountId) {
        LOGGER_INFO.info("Выдан запрос на поиск всех Транзакций по ID = " + accountId + " счета!");
        try {
            List <Transaction> accountTransactions = sessionFactory.getCurrentSession().
                    createQuery("from Transaction tr where tr.accountId = :id")
                    .setParameter("id", accountId)
                    .getResultList();
            LOGGER_INFO.info("Запрос на поиск всех Транзакций по ID = " + accountId + " счета выполнен!");
            return accountTransactions;
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось найти всех Транзакций по ID = " + accountId + ". Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось найти всех Транзакций по ID = " + accountId);
        }
    }

    @Override
    public void update(Transaction entity) {}

    @Override
    public void delete(Long id) {}

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Transaction findById(Long id) {
        return null;
    }
}