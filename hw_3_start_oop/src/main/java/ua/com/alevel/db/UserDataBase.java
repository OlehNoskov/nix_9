package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.Arrays;
import java.util.UUID;

public class UserDataBase {
    private int index = 0;
    private final int DEFAULT_SIZE = 3;
    private final double DEFAULT_LOAD = 0.75;
    private User[] users;
    private static UserDataBase instance;

    private UserDataBase() {
        users = new User[DEFAULT_SIZE];
    }

    private void increaseArrayUsers(User user) {
        if (index >= users.length * DEFAULT_LOAD) {
            users = Arrays.copyOf(users, users.length + 3);
        }
        users[index++] = user;
    }

//    private void decreaseArrayUsers() {
//        User[] copyUserArray = Arrays.copyOf(users, users.length);
//        users = new User[users.length - 1];
//        int count = 0;
//        for (User user : copyUserArray) {
//            if (user != null){
//                users[count] = user;
//                count++;
//            }
//        }
//    }

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
        User deleteUser = findById(id);
        int index = -1;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId().equals(String.valueOf(deleteUser.getId()))) {
                users[i] = null;
                index = i;
            }
        }
        User arrayUserDeleted[] = new User[users.length - 1];
        for (int i = 0; i < index; i++) {
            arrayUserDeleted[i] = users[i];
        }
        for (int i = index; i < arrayUserDeleted.length; i++) {
            arrayUserDeleted[i] = users[i + 1];
        }
        users = Arrays.copyOf(arrayUserDeleted, users.length - 1);

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
