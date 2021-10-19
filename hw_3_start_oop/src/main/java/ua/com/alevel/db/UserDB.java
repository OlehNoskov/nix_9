package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDB {
    private final User[] users;
    private static UserDB instance;

    private UserDB() {
        users = new User[10];
    }

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateId());
        for (int i = users.length - 1; i >= 0; i++) {
            users[i] = user;
        }
    }

    public void update(User user) {
        User current = findById(user.getId());
        current.setAge(user.getAge());
        current.setName(user.getName());
    }

    public void delete(String id) {
        for(int i=0;i< users.length;i++){
            if(users[i].getId().equals(id))
                users[i]=null;
        }
//        users.removeIf(user -> user.getId().equals(id));
    }

    public User findById(String id) {
        User result=new User();

        for (int i = 0; i < users.length; i++) {
            if (id.equals(users[i].getId()))
                result = users[i];
        }
        return result;
    }

//        return users.stream()
//                .filter(u -> u.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("user not found by id"));


    public User[] findAll() {
        return users;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();

        for(User user:users){
            if (user.getId().equals(id)){
                return generateId();
            }
        }
//        if (users.stream().anyMatch(user -> user.getId().equals(id))) {
//            return generateId();
//        }
        return id;
    }
}
