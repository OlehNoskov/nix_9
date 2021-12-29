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


@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class Module3Application {

//    private final JpaConfig jpaConfig;
//
//    public Module3Application(JpaConfig jpaConfig, UserDao userDao, OperationDao operationDao, AccountDao accountDao, TransactionDao transactionDao) {
//        this.jpaConfig = jpaConfig;
//        this.userDao = userDao;
//        this.operationDao = operationDao;
//        this.accountDao = accountDao;
//        this.transactionDao = transactionDao;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Module3Application.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    @Order(value = 1)
//    public void initDB() {
//        jpaConfig.connect();
//    }
//
//    private final UserDao userDao;
//    private final OperationDao operationDao;
//    private final AccountDao accountDao;
//    private final TransactionDao transactionDao;
//
//    @EventListener(ApplicationReadyEvent.class)
//    @Order(value = 3)
//    public void initTables() {
//        try{
//            User testUs1 = new User("Крокодил", "Гена", 45); userDao.create(testUs1);
//            User testUs2 = new User("Чебурашка", "Безродный", 1); userDao.create(testUs2);
//            User testUs3 = new User("Старуха", "Шапокляк", 106); userDao.create(testUs3);
//            User testUs4 = new User("Красная", "Шапочка", 12); userDao.create(testUs4);
//            User testUs5 = new User("Серый", "v", 25); userDao.create(testUs5);
//            User testUs6 = new User("Алеша", "Попович", 20); userDao.create(testUs6);
//            User testUs7 = new User("Тугарин", "Змей", 40); userDao.create(testUs7);
//            User testUs8 = new User("Максимка", "Местный", 26); userDao.create(testUs8);
//
//            Operation testOp1 = new Operation("Подработать на стройке", true); operationDao.create(testOp1);
//            Operation testOp2 = new Operation("Отжать и продать пирожки", true); operationDao.create(testOp2);
//            Operation testOp3 = new Operation("Поиграть на баяне", true); operationDao.create(testOp3);
//            Operation testOp4 = new Operation("Грабануть Деревню", true); operationDao.create(testOp4);
//            Operation testOp5 = new Operation("Продать тараканов", true); operationDao.create(testOp5);
//            Operation testOp6 = new Operation("Потерять золотой зуб", false); operationDao.create(testOp6);
//            Operation testOp7 = new Operation("Отдать деньги", false); operationDao.create(testOp7);
//            Operation testOp8 = new Operation("Заплатить за проезд", false); operationDao.create(testOp8);
//            Operation testOp9 = new Operation("Купить модных шмоток", false); operationDao.create(testOp9);
//            Operation testOp10 = new Operation("Купить то не знаю что", false); operationDao.create(testOp10);
//            Operation testOp11 = new Operation("Купить Кахфу", false); operationDao.create(testOp11);
//            Operation testOp12 = new Operation("Купить сосиску", false); operationDao.create(testOp12);
//
//            Account testAcc1 = new Account(1L, "7563 0934 3195 1487", new BigDecimal("10000")); accountDao.create(testAcc1);
//            Account testAcc2 = new Account(1L, "6337 1286 4455 1570", new BigDecimal("10000")); accountDao.create(testAcc2);
//            Account testAcc3 = new Account(2L, "7641 2587 6547 7777", new BigDecimal("10000")); accountDao.create(testAcc3);
//
//            Transaction testTr1 = new Transaction(1L,1L,1L, new BigDecimal("1000"), "Гена Крокодил", "7563 0934 3195 1487", "Подработать на стройке", true);
//            transactionDao.create(testTr1);
//            Transaction testTr2 = new Transaction(1L,1L,3L, new BigDecimal("2500"), "Гена Крокодил", "7563 0934 3195 1487", "Поиграть на баяне", true);
//            transactionDao.create(testTr2);
//            Transaction testTr3 = new Transaction(1L,1L,11L, new BigDecimal("5000"), "Гена Крокодил", "7563 0934 3195 1487", "Купить Кахфу", false);
//            transactionDao.create(testTr3);
//
//            testAcc1.setBalance(new BigDecimal("8500"));accountDao.update(testAcc1);
//
//        }catch (Exception ex) {
//            System.out.println();
//        }
//    }
}