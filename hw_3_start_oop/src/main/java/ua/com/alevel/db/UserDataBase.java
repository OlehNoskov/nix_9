package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.Arrays;
import java.util.UUID;

public class UserDataBase {
    private int index = 0;
    private final int DEFAULT_SIZE = 5;
    private final double DEFAULT_LOAD = 0.75;
    private User[] users;
    private static UserDataBase instance;

    private UserDataBase() {
        users = new User[DEFAULT_SIZE];
    }

    private void increaseArrayUsers(User user) {
        if (index >= users.length * DEFAULT_LOAD) {
            users = Arrays.copyOf(users, users.length + 5);
        }
        users[index++] = user;
    }

    public static UserDataBase getInstance() {
        if (instance == null) {
            instance = new UserDataBase();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateId());
        increaseArrayUsers(user);
    }

    public void update(User user) {
        User currentUser = findById(user.getId());
        currentUser.setName(user.getName());
        currentUser.setLastName(user.getLastName());
        currentUser.setAge(user.getAge());
    }

    public void delete(String id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId().equals(id)) {
                users[i] = null;
            }
        }
    }

    public User findById(String id) {
        for (User user : users) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }

    public User[] findAllUsers() {
        return users;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
