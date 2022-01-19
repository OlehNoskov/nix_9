package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Statement;
import ua.com.alevel.persistence.entity.AccountsData;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao {

    private final JpaConfig jpaConfig;
    private final SessionFactory sessionFactory;
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public AccountDaoImpl(SessionFactory sessionFactory, JpaConfig jpaConfig) {
        this.sessionFactory = sessionFactory;
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Account entity) {
        try {
            sessionFactory.getCurrentSession().persist(entity);
        } catch (Exception he) {
            LOGGER_ERROR.error("Failed to create an account!" + he.getMessage());
            throw new RuntimeException("Contact the administrator!");
        }
    }

    @Override
    public void create(Long id) {
        LOGGER_INFO.info("Creating a New Account!");
        int uahBalance = 100 * 100;
        StringBuilder accountNumbers = new StringBuilder();
        for (int i = 0; i <= 3; i++) {
            accountNumbers.append(String.valueOf(rnd())).append(" ");
        }

        Account account = new Account();
        account.setUserId(id);
        account.setBalance(new BigDecimal(String.valueOf(uahBalance)));
        account.setAccountNumbers(accountNumbers.toString());

        try {
            sessionFactory.getCurrentSession().persist(account);
        } catch (Exception he)/*HibernateException*/ {
            LOGGER_ERROR.error("Failed to create an account!" + he.getMessage());
            throw new RuntimeException("Contact the administrator!");
        }
        LOGGER_INFO.info("Accounts created");
    }

    private int rnd() {
        int min = 1000;
        int max = 9999;
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    @Override
    public void update(Account entity) {
        LOGGER_INFO.info("Account update:" + entity.getAccountNumbers());
        try {
            sessionFactory.getCurrentSession().merge(entity);
        } catch (Exception he) {
            LOGGER_ERROR.error("Error!" + entity.getAccountNumbers() + "" + he.getMessage());
            throw new RuntimeException("Operation failed!");
        }
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("Deleting an account");
        try {
            sessionFactory.getCurrentSession().createQuery("delete from Account acc where acc.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception he) {
            LOGGER_ERROR.error("Failed to delete account!");
            throw new RuntimeException("Failed to delete account!");
        }
        LOGGER_INFO.info("Account deleted!");
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Account findById(Long id) {
        LOGGER_INFO.info("Account Search!");
        try {
            Account account = sessionFactory.getCurrentSession().find(Account.class, id);
            return account;
        }catch (Exception he) {
            LOGGER_ERROR.error("Account not found!");
            throw new RuntimeException("Account not found!");
        }
    }

    @Override
    public List<Account> findAll() {
        LOGGER_INFO.info("Search all accounts");
        List<Account> accountsAll = new LinkedList<>();

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
            Root<Account> from = criteriaQuery.from(Account.class);
            accountsAll = session.createQuery(criteriaQuery).getResultList();
        }catch (Exception he) {
            LOGGER_ERROR.error("Could not find all accounts!");
            throw new RuntimeException("Could not find all accounts!");
        }
        return accountsAll;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Account> findByUserId(Long id) {
        LOGGER_INFO.info("Поиск Счета");
        try {
            List<Account> userAccounts = sessionFactory.getCurrentSession().createQuery("from Account acc where acc.userId = :id")
                    .setParameter("id", id)
                    .getResultList();
            return userAccounts;
        }catch (Exception he) {
            LOGGER_ERROR.error("Could not find accounts!");
            throw new RuntimeException("Could not find accounts");
        }
    }

    @Override
    public List<AccountsData> getAccountStatementFileForDownload(Statement statement) {
        LOGGER_INFO.info("Create account statement");
        String sql = "SELECT created, transaction_sum, category_name, category_income_expense " +
                "FROM transactions " +
                "WHERE account_id = " + statement.getId() + " " +
                "AND created BETWEEN '" + statement.getBeginDate() +
                "' AND '" + statement.getEndDate() + "'";

        List<AccountsData> accountForFileList = new LinkedList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                accountForFileList.add(convertResultSetToAccountStatement(resultSet));
            }
        } catch (SQLException e) {
            LOGGER_ERROR.error("Failed to complete the operation to create an account statement!");
            throw new RuntimeException("Failed to complete the operation to create an account statement!");
        }
        return accountForFileList;
    }

    private AccountsData convertResultSetToAccountStatement(ResultSet resultSet) throws SQLException {
        AccountsData accountStatement = new AccountsData(
                resultSet.getDate("created"),
                resultSet.getBigDecimal("transaction_sum"),
                resultSet.getString("category_name"),
                resultSet.getBoolean("category_income_expense"));
        return accountStatement;
    }
}