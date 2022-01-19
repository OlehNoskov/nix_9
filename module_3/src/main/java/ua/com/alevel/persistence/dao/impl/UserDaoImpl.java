package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User entity) {
        LOGGER_INFO.info("A request to create a new User has been issued!");
        try {
            sessionFactory.getCurrentSession().persist(entity);
        }catch (Exception e) {
            LOGGER_ERROR.error("Failed to create new User! Cause: " + e.getMessage());
            throw new RuntimeException("Failed to create new User! Contact the administrator!");
        }
        LOGGER_INFO.info("The request to create a new User has been completed!");
    }

    @Override
    public void update(User entity) {
        LOGGER_INFO.info("User update request issued");
        try {
            sessionFactory.getCurrentSession().merge(entity);
        } catch (Exception e) {
            LOGGER_ERROR.error("User update operation failed " + "! Cause: " + e.getMessage());
            throw new RuntimeException("User update operation failed!");
        }
        LOGGER_INFO.info("Request for Update Account operation completed!");

    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("A request was issued to delete a User by ID =  " + id);
        try {
            sessionFactory.getCurrentSession().createQuery("delete from User us where us.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        }catch (Exception he) {
            LOGGER_ERROR.error("Failed to delete User " + id + "! Cause: " + he.getMessage());
            throw new RuntimeException("Failed to delete User " + id + "!");
        }
        LOGGER_INFO.info("Request to delete a User by ID =  " + id + " completed!");

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public User findById(Long id) {
        LOGGER_INFO.info("A request was issued to search for a User by ID =  " + id);
        try {
            User user = sessionFactory.getCurrentSession().find(User.class, id);
            LOGGER_INFO.info("Request to search for a User by ID =  " + id + " completed!");
            return user;
        }catch (Exception he) {
            LOGGER_ERROR.error("Failed to find User by " + id + "! Cause: " + he.getMessage());
            throw new RuntimeException("Failed to find User by " + id + "!");
        }
    }

    @Override
    public List<User> findAll() {
        LOGGER_INFO.info("Requested to search for all Users");
        List<User> usersAll;

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> from = criteriaQuery.from(User.class);
            usersAll = session.createQuery(criteriaQuery).getResultList();
        }catch (Exception e) {
            LOGGER_ERROR.error("Could not find all Users! Cause: " + e.getMessage());
            throw new RuntimeException("Could not find all Users!");
        }
        LOGGER_INFO.info("Request to search for all Users completed!");
        return usersAll;
    }

    @Override
    public long count() {
        return 0;
    }
}