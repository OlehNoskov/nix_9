package ua.com.alevel.service;

import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.entity.User;

public class UserService {
    private final UserDao userDao = new UserDao();

    public void create(User user) {
        userDao.create(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(String id) {
        userDao.delete(id);
    }

    public User findById(String id) {
        return userDao.findById(id);
    }

    public User[] findAllUsers() {
        return userDao.findAllUsers();
    }
}
