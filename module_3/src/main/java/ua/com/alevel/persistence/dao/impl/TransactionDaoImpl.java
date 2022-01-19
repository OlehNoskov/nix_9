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
        LOGGER_INFO.info("A request to create a new Transaction has been issued!");
        try {
            sessionFactory.getCurrentSession().persist(entity);
        }catch (Exception e){
            LOGGER_ERROR.error("Failed to create new Transaction! Cause: " + e.getMessage());
            throw new RuntimeException("Failed to create new Transaction! Contact the administrator!");
        }
        LOGGER_INFO.info("The request to create a new Transaction has been completed!");
    }

    @Override
    public List<Transaction> findAll() {
        LOGGER_INFO.info("Requested to search for all Transactions");
        List<Transaction> transactionsAll;

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
            Root<Transaction> from = criteriaQuery.from(Transaction.class);
            transactionsAll = session.createQuery(criteriaQuery).getResultList();
        }catch (Exception he) {
            LOGGER_ERROR.error("Could not find all Transactions! Cause: " + he.getMessage());
            throw new RuntimeException("Could not find all Transactions!");
        }

        LOGGER_INFO.info("The request to search for all Transactions has been completed!");
        return transactionsAll;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Transaction> findTransactionsByUserId(Long userId) {
        LOGGER_INFO.info("A request was issued to search for all Transactions by ID = " + userId + " user!");
        try {
            List <Transaction> userTransactions = sessionFactory.getCurrentSession().
                    createQuery("from Transaction tr where tr.userId = :id")
                    .setParameter("id", userId)
                    .getResultList();
            LOGGER_INFO.info("Request to search for all Transactions by ID = " + userId + " user completed!");
            return userTransactions;
        }catch (Exception e) {
            LOGGER_ERROR.error("Failed to find all Transactions by ID = " + userId + ". Cause: " + e.getMessage());
            throw new RuntimeException("Failed to find all Transactions by ID = " + userId);
        }
    }

    @Override
    public List<Transaction> findTransactionsByAccountId(Long accountId) {
        LOGGER_INFO.info("A request was issued to search for all Transactions by ID = " + accountId + " accounts!");
        try {
            List <Transaction> accountTransactions = sessionFactory.getCurrentSession().
                    createQuery("from Transaction tr where tr.accountId = :id")
                    .setParameter("id", accountId)
                    .getResultList();
            LOGGER_INFO.info("Request to search for all Transactions by ID = " + accountId + " user completed!!");
            return accountTransactions;
        }catch (Exception he) {
            LOGGER_ERROR.error("Failed to find all Transactions by ID = " + accountId + ". Cause: " + he.getMessage());
            throw new RuntimeException("Failed to find all Transactions by ID = " + accountId);
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