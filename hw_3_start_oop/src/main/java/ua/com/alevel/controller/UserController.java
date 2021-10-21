package ua.com.alevel.controller;

import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserController {

    private final UserService userService = new UserService();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите свой вариант:");
        String choice;
        try {
            showMenuProgram();
            while ((choice = reader.readLine()) != null) {
                crud(choice, reader);
                choice = reader.readLine();
                if (choice.equals("0")) {
                    System.exit(0);
                }
                crud(choice, reader);
            }
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }

    private void showMenuProgram() {
        System.out.println();
        System.out.println("Если хотите создать пользователя, нажмите 1");
        System.out.println("Если хотите обновить пользователя, нажмите 2");
        System.out.println("Если хотите удалить пользователя, нажмите 3");
        System.out.println("Если хотите найти пользователя по ID, нажмите 4");
        System.out.println("Если хотите найти всех пользователей, нажмите 5");
        System.out.println("Для выхода из программы нажмите 0");
        System.out.println();
    }

    private void crud(String choice, BufferedReader reader) {
        switch (choice) {
            case "1":
                create(reader);
                break;
            case "2":
                update(reader);
                break;
            case "3":
                delete(reader);
                break;
            case "4":
                findById(reader);
                break;
            case "5":
                findAll(reader);
                break;
        }
        showMenuProgram();
    }

    private void create(BufferedReader reader) {
        System.out.println("Создание нового пользователя:");
        try {
            System.out.println("Введите свое имя");
            String name = reader.readLine();
            System.out.println("Введите свою фамилию");
            String lastname = reader.readLine();
            System.out.println("Введите свой возраст");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            User user = new User();
            user.setName(name);
            user.setLastName(lastname);
            user.setAge(age);
            userService.create(user);
            System.out.println("Новый пользователь " + user.getName() + " был успешно создан!");
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Некорректно введен возраст!");
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("Обновление данных пользователя:");
        try {
            System.out.println("Пожайлуста введите ID пользователя");
            String id = reader.readLine();
            System.out.println("Введите имя пользователя");
            String name = reader.readLine();
            System.out.println("Введите фамилию пользователя");
            String lastname = reader.readLine();
            System.out.println("Введите возраст пользователя");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setLastName(lastname);
            user.setAge(age);
            userService.update(user);
            System.out.println("Данные пользователя обновлены!");
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Некорректно введен возраст!");
        } catch (NullPointerException e) {
            System.out.println("Данный пользователь не найден!");
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("Удаление пользователя:");
        try {
            System.out.println("Введите ID пользователя");
            String id = reader.readLine();
            userService.delete(id);
        } catch (IOException e) {
            System.out.println("Возникла ошибка: = " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Пользователь был успешно удалён!");
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("Поиск пользователя по ID:");
        try {
            System.out.println("Введите ID пользователя");
            String id = reader.readLine();
            User user = userService.findById(id);
            System.out.println("Пользователь = " + user);
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Пользователь не найден!");
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("Поиск всех пользователей:");
        User[] users = userService.findAllUsers();
        if (users != null && users.length != 0) {
            for (User user : users) {
                System.out.println("Пользователь = " + user);
            }
        } else
            System.out.println("В данный момент список пользователей пуст.");
    }
}
