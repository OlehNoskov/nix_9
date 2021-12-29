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
import ua.com.alevel.persistence.entity.AccountStatement;
import ua.com.alevel.persistence.entity.AccountStatementForFile;

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
        } catch (Exception he)/*HibernateException*/ {
            LOGGER_ERROR.error("Не удалось создать новый Счет! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось создать новый Счет! Обратитесь к администратору!");
        }
    }

    @Override
    public void create(Long id) {
        LOGGER_INFO.info("Выдан запрос на создание нового Счета!");
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
            LOGGER_ERROR.error("Не удалось создать новый Счет! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось создать новый Счет! Обратитесь к администратору!");
        }

        LOGGER_INFO.info("Запрос на создание нового Счета выполнен!");
    }

    private int rnd() {
        int min = 1000;
        int max = 9999;
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    @Override
    public void update(Account entity) {
        LOGGER_INFO.info("Выдан запрос на операцию со счетом " + entity.getAccountNumbers());
        try {
            sessionFactory.getCurrentSession().merge(entity);
        } catch (Exception he) {
            LOGGER_ERROR.error("Не удалось выполнить операцию со счетом " + entity.getAccountNumbers() + "! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось выполнить операцию со счетом " + entity.getAccountNumbers() + "!");
        }
        LOGGER_INFO.info("Запрос на операцию со счетом " + entity.getAccountNumbers() + " выполнен!");
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("Выдан запрос на удаление счета по ID =  " + id);
        try {
            sessionFactory.getCurrentSession().createQuery("delete from Account acc where acc.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception he) {
            LOGGER_ERROR.error("Не удалось удалить счет " + id + "! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось удалить счет " + id + "!");
        }
        LOGGER_INFO.info("Запрос на удаление счета по ID =  " + id + " выполнен!");
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Account findById(Long id) {
        LOGGER_INFO.info("Выдан запрос на поиск счета по ID =  " + id);
        try {
            Account account = sessionFactory.getCurrentSession().find(Account.class, id);
            LOGGER_INFO.info("Запрос на поиск счета по ID =  " + id + " выполнен!");
            return account;
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось найти счет по " + id + "! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось найти счет по " + id + "!");
        }
    }

    @Override
    public List<Account> findAll() {
        LOGGER_INFO.info("Выдан запрос на поиск всех Счетов");
        List<Account> accountsAll = new LinkedList<>();

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
            Root<Account> from = criteriaQuery.from(Account.class);
            accountsAll = session.createQuery(criteriaQuery).getResultList();
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось найти все счета! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось найти все счета!");
        }

        LOGGER_INFO.info("Запрос на поиск всех Счетов выполнен!");
        return accountsAll;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Account> findByUserId(Long id) {
        LOGGER_INFO.info("Выдан запрос на поиск Счета по ID = " + id + " пользователя!");
        try {
            List<Account> userAccounts = sessionFactory.getCurrentSession().createQuery("from Account acc where acc.userId = :id")
                    .setParameter("id", id)
                    .getResultList();
            LOGGER_INFO.info("Запрос на поиск Счета по ID = " + id + " пользователя выполнен!");
            return userAccounts;
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось найти счет по ID = " + id + " Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось найти счет по ID = " + id + "пользователя!");
        }
    }

    @Override
    public List<AccountStatementForFile> getAccountStatementFileForDownload(AccountStatement accountStatement) {
        LOGGER_INFO.info("Выдан запрос на создание выписки по счету ID = " + accountStatement.getId() + "!");
        String sql = "SELECT created, transaction_sum, category_name, category_income_expense " +
                "FROM transactions " +
                "WHERE account_id = " + accountStatement.getId() + " " +
                "AND created BETWEEN '" + accountStatement.getDateFrom() +
                "' AND '" + accountStatement.getDateTo() + "'";

        List<AccountStatementForFile> accountStatementForFileList = new LinkedList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                accountStatementForFileList.add(convertResultSetToAccountStatement(resultSet));
            }
        } catch (SQLException e) {
            LOGGER_ERROR.error("Не удалось выполнить операцию на создание выписки по счету ID = " +
                    accountStatement.getId() + " Причина: " + e.getMessage());
            throw new RuntimeException("Не удалось выполнить операцию на создание выписки по счету ID = " +
                    accountStatement.getId() + "!");
        }
        LOGGER_INFO.info("Запрос на создание выписки по счету ID = " + accountStatement.getId() + " выполнен!");
        return accountStatementForFileList;
    }

    private AccountStatementForFile convertResultSetToAccountStatement(ResultSet resultSet) throws SQLException {
        AccountStatementForFile accountStatement = new AccountStatementForFile(
                resultSet.getDate("created"),
                resultSet.getBigDecimal("transaction_sum"),
                resultSet.getString("category_name"),
                resultSet.getBoolean("category_income_expense"));
        return accountStatement;
    }
}