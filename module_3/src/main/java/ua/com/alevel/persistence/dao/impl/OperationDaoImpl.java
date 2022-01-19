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
        LOGGER_INFO.info("A request to create a new operation has been issued!");
        try {
            sessionFactory.getCurrentSession().persist(entity);
        }catch (Exception e) {
            LOGGER_ERROR.error("Failed to create new Operation! Cause: " + e.getMessage());
            throw new RuntimeException("Failed to create new Operation! Contact the administrator!");
        }
        LOGGER_INFO.info("The request to create a new operation has been completed!");
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("A request was issued to delete an operation by ID =  " + id);
        try {
            sessionFactory.getCurrentSession().createQuery("delete from Operation op where op.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        }catch (Exception he) {
            LOGGER_ERROR.error("Failed operation account " + id + "! Cause: " + he.getMessage());
            throw new RuntimeException("Failed operation account " + id + "!");
        }
        LOGGER_INFO.info("Request to delete a transaction ID =  " + id + " completed!");
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Operation findById(Long id) {
        LOGGER_INFO.info("A request was issued to search for an operation by ID =  " + id);
        try {
            Operation operation = sessionFactory.getCurrentSession().find(Operation.class, id);
            LOGGER_INFO.info("Request to search for an operation by ID =  " + id + " completed!");
            return operation;
        }catch (Exception he) {
            LOGGER_ERROR.error("Failed to find operation " + id + "! Cause: " + he.getMessage());
            throw new RuntimeException("Failed to find operation " + id + "!");
        }
    }

    @Override
    public List<Operation> findAll() {
        LOGGER_INFO.info("A request to search for all Operations has been issued");
        List<Operation> operationsAll;

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Operation> criteriaQuery = criteriaBuilder.createQuery(Operation.class);
            Root<Operation> from = criteriaQuery.from(Operation.class);
            operationsAll = session.createQuery(criteriaQuery).getResultList();
        }catch (Exception he) {
            LOGGER_ERROR.error("Could not find all operations! Cause: " + he.getMessage());
            throw new RuntimeException("Could not find all operations!");
        }

        LOGGER_INFO.info("The request to search for all Operations has been completed!");
        return operationsAll;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void update(Operation entity) {}
}