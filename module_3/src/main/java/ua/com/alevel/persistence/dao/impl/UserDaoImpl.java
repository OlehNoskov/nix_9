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
import java.util.LinkedList;
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
        LOGGER_INFO.info("Выдан запрос на создание нового Пользователя!");
        try {
            sessionFactory.getCurrentSession().persist(entity);
        }catch (Exception he /*HibernateException*/) {
            LOGGER_ERROR.error("Не удалось создать нового Пользователя! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось создать нового Пользователя! Обратитесь к администратору!");
        }
        LOGGER_INFO.info("Запрос на создание нового Пользователя выполнен!");
    }

    @Override
    public void update(User entity) {
        LOGGER_INFO.info("Выдан запрос на обновление Пользователя ");
        try {
            sessionFactory.getCurrentSession().merge(entity);
        } catch (Exception he) {
            LOGGER_ERROR.error("Не удалось выполнить операцию обновления Пользователя " + "! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось выполнить операцию обновления Пользователя " + "!");
        }
        LOGGER_INFO.info("Запрос на операцию обновления Пользователя " + " выполнен!");

    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("Выдан запрос на удаление Пользователя по ID =  " + id);
        try {
            sessionFactory.getCurrentSession().createQuery("delete from User us where us.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось удалить Пользователя " + id + "! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось удалить Пользователя " + id + "!");
        }
        LOGGER_INFO.info("Запрос на удаление Пользователя по ID =  " + id + " выполнен!");

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public User findById(Long id) {
        LOGGER_INFO.info("Выдан запрос на поиск Пользователя по ID =  " + id);
        try {
            User user = sessionFactory.getCurrentSession().find(User.class, id);
            LOGGER_INFO.info("Запрос на поиск Пользователя по ID =  " + id + " выполнен!");
            return user;
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось найти Пользователя по " + id + "! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось найти Пользователя по " + id + "!");
        }
    }

    @Override
    public List<User> findAll() {
        LOGGER_INFO.info("Выдан запрос на поиск всех Пользователей");
        List<User> usersAll = new LinkedList<>();

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> from = criteriaQuery.from(User.class);
            usersAll = session.createQuery(criteriaQuery).getResultList();
        }catch (Exception he) {
            LOGGER_ERROR.error("Не удалось найти всех Пользователей! Причина: " + he.getMessage());
            throw new RuntimeException("Не удалось найти всех Пользователей!");
        }
        LOGGER_INFO.info("Запрос на поиск всех Пользователей выполнен!");
        return usersAll;
    }

    @Override
    public long count() {
        return 0;
    }
}