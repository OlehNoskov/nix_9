package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.OperationDao;
import ua.com.alevel.persistence.entity.Operation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
public class OperationDaoImpl implements OperationDao {

    private final SessionFactory sessionFactory;
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public OperationDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Operation entity) {
        LOGGER_INFO.info("Выдан запрос на создание новой операции!");
        try {
            sessionFactory.getCurrentSession().persist(entity);
        }catch (Exception he)/*Exception*/ {
            LOGGER_ERROR.error("Не удалось создать новую Операцию! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось создать новую Операцию! Обратитесь к администратору!");
        }
        LOGGER_INFO.info("Запрос на создание новой операции выполнен!");
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("Выдан запрос на удаление операции по ID =  " + id);
        try {
            sessionFactory.getCurrentSession().createQuery("delete from Operation op where op.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось операцию счет " + id + "! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось операцию счет " + id + "!");
        }
        LOGGER_INFO.info("Запрос на удаление операции по ID =  " + id + " выполнен!");
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Operation findById(Long id) {
        LOGGER_INFO.info("Выдан запрос на поиск операции по ID =  " + id);
        try {
            Operation operation = sessionFactory.getCurrentSession().find(Operation.class, id);
            LOGGER_INFO.info("Запрос на поиск операции по ID =  " + id + " выполнен!");
            return operation;
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось найти операцию по " + id + "! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось найти операцию по " + id + "!");
        }
    }

    @Override
    public List<Operation> findAll() {
        LOGGER_INFO.info("Выдан запрос на поиск всех Операций");
        List<Operation> operationsAll = new LinkedList<>();

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Operation> criteriaQuery = criteriaBuilder.createQuery(Operation.class);
            Root<Operation> from = criteriaQuery.from(Operation.class);
            operationsAll = session.createQuery(criteriaQuery).getResultList();
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось найти все операции! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось найти все операции!");
        }

        LOGGER_INFO.info("Запрос на поиск всех Операций выполнен!");
        return operationsAll;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void update(Operation entity) {}
}