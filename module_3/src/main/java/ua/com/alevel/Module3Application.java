package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.dao.OperationDao;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.persistence.entity.User;

import java.math.BigDecimal;
import java.sql.Timestamp;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class Module3Application {

    private final JpaConfig jpaConfig;
    private final UserDao userDao;
    private final OperationDao operationDao;
    private final AccountDao accountDao;
    private final TransactionDao transactionDao;

    public Module3Application(JpaConfig jpaConfig, UserDao userDao,
                              OperationDao operationDao,
                              AccountDao accountDao,
                              TransactionDao transactionDao) {
        this.jpaConfig = jpaConfig;
        this.userDao = userDao;
        this.operationDao = operationDao;
        this.accountDao = accountDao;
        this.transactionDao = transactionDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(Module3Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Order(value = 1)
    public void initDB() {
        jpaConfig.connect();
    }

    @EventListener(ApplicationReadyEvent.class)
    @Order(value = 3)
    public void initTables() {
        try {
            User user1 = new User("Noskov", "Oleh", 28);
            userDao.create(user1);
            user1.setCreated(new Timestamp(System.currentTimeMillis()));

            User user2 = new User("Funtusov", "Iegor", 30);
            userDao.create(user2);
            user2.setCreated(new Timestamp(System.currentTimeMillis()));

            User user3 = new User("Rastvorceva", "Irina", 27);
            userDao.create(user3);
            user3.setCreated(new Timestamp(System.currentTimeMillis()));

            User user4 = new User("Prihodko", "Ilona", 19);
            userDao.create(user4);
            user4.setCreated(new Timestamp(System.currentTimeMillis()));

            User user5 = new User("Kysil", "Sergey", 29);
            userDao.create(user5);
            user5.setCreated(new Timestamp(System.currentTimeMillis()));

            Operation operation1 = new Operation("Salary", true);
            operationDao.create(operation1);
            operation1.setCreated(new Timestamp(System.currentTimeMillis()));

            Operation operation2 = new Operation("Bonus", true);
            operationDao.create(operation2);
            operation2.setCreated(new Timestamp(System.currentTimeMillis()));

            Operation operation3 = new Operation("Present", true);
            operationDao.create(operation3);
            operation3.setCreated(new Timestamp(System.currentTimeMillis()));

            Operation operation4 = new Operation("Sale of things", true);
            operationDao.create(operation4);
            operation4.setCreated(new Timestamp(System.currentTimeMillis()));

            Operation operation5 = new Operation("Shopping in the store", false);
            operationDao.create(operation5);
            operation4.setCreated(new Timestamp(System.currentTimeMillis()));

            Operation operation6 = new Operation("Debt repayment", false);
            operationDao.create(operation6);
            operation6.setCreated(new Timestamp(System.currentTimeMillis()));

            Account account1 = new Account(1L, "7563 0934 3195 1487", new BigDecimal("1000"));
            accountDao.create(account1);
            account1.setCreated(new Timestamp(System.currentTimeMillis()));

            Account account2 = new Account(1L, "6337 1286 4455 1570", new BigDecimal("1000"));
            accountDao.create(account2);
            account2.setCreated(new Timestamp(System.currentTimeMillis()));

            Account account3 = new Account(2L, "7641 2587 6547 7777", new BigDecimal("1000"));
            accountDao.create(account3);
            account3.setCreated(new Timestamp(System.currentTimeMillis()));

            Transaction transaction1 = new Transaction(1L, 1L, 1L, new BigDecimal("1000"), "Oleh Noskov", "7563 0934 3195 1487", "Salary", true);
            transactionDao.create(transaction1);
            transaction1.setCreated(new Timestamp(System.currentTimeMillis()));

            Transaction transaction2 = new Transaction(1L, 1L, 3L, new BigDecimal("2500"), "Ilona Prihodko", "7563 0934 3195 1487", "Present", true);
            transactionDao.create(transaction2);
            transaction2.setCreated(new Timestamp(System.currentTimeMillis()));

            Transaction transaction3 = new Transaction(1L, 1L, 11L, new BigDecimal("5000"), "Iegor Funtusov", "7563 0934 3195 1487", "Shopping in the store", false);
            transactionDao.create(transaction3);
            transaction3.setCreated(new Timestamp(System.currentTimeMillis()));
        } catch (Exception ex) {
            System.out.println();
        }
    }
}