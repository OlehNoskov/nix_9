package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.EmployeeController;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.service.impl.DepartmentServiceImpl;
import ua.com.alevel.service.impl.EmployeeServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmployeeControllerImpl implements EmployeeController {

    EmployeeService employeeService  =new EmployeeServiceImpl();
    DepartmentControllerImpl departmentController = new DepartmentControllerImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            showMenu();
            while ((position = reader.readLine()) != null && !position.equals("0")) {
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }
    private void showMenu() {
        System.out.println();
        System.out.println("===== МЕНЮ Сотрудника =====");
        System.out.println("Нажмите 1: Создание нового сотрудника");
        System.out.println("Нажмите 2: Изменения данных сотрудника");
        System.out.println("Нажмите 3: Удаление сотрудника");
        System.out.println("Нажмите 4: Поиска сотрудника по Id");
        System.out.println("Нажмите 5: Поиск всех сотрудников");
        System.out.println("Нажмите 6: Поиск сотрудника по указанному департаменту");
        System.out.println("Нажмите 0: Выхода в Главное Меню");
    }

    private void crud(String choiceMenu, BufferedReader reader) {
        switch (choiceMenu) {
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
                findAllBooks();
                break;
            case "6":
                findAllAuthorBooks(reader);
                break;
        }
        showMenu();
    }

    private void create(BufferedReader reader) {
        System.out.println("Введите название новой книги:");
        try {
            String name = reader.readLine();
            if (!name.isEmpty()) {
                Employee employee = selectOrCreateDepartment(reader);
                if (employee == null) {
                    System.out.println("Не удалось создать книгу, автор не указан!");
                    return;
                }
                Book book = new Book();
                book.setBookTitle(name);
                book.setAuthor(author);
                bookService.create(book);
            } else {
                System.out.println("Вы не ввели название книги!");
            }
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("Введите Id книги, которую хотите изменить:");
        try {
            String idBook = reader.readLine();
            Book book = bookService.findById(idBook);
            if (book == null) {
                System.out.println("Id не был введен корректно");
                return;
            }
            System.out.println("Введите новое название книги:");
            String name = reader.readLine();
            if (!name.isEmpty()) {
                Author author = selectOrCreateAuthor(reader);
                if (author == null) {
                    System.out.println("Не удалось создать книгу, автор не указан!");
                    return;
                }
                book.setBookTitle(name);
                book.setAuthor(author);
                bookService.update(book);
            } else {
                System.out.println("Вы не ввели название книги!");
            }
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("Введите Id книги, которую хотите удалить:");
        try {
            String idBook = reader.readLine();
            if (!idBook.isEmpty()) {
                Book book;
                book = bookService.findById(idBook);
                if (book == null) {
                    System.out.println("Некорректный ID");
                    return;
                }
                boolean deleteResult = bookService.delete(book.getId());
                if (deleteResult) {
                    System.out.println("Книга удалена!");
                }
            } else {
                System.out.println("Книга не найдена!");
            }
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        Book book;
        System.out.println("Введите ID книги, которую хотите найти:");
        try {
            String idBook = reader.readLine();
            if (!idBook.isEmpty()) {
                book = bookService.findById(idBook);
                if (book == null) {
                    System.out.println("Некорректный ID");
                } else
                    System.out.println(book);
            }
        } catch (IOException e) {
            System.out.println("Error+ " + e.getMessage());
        }
    }

    private void findAllBooks() {
        Book[] books = bookService.findAllBooks();
        if (books != null && books.length != 0) {
            for (Book book : books) {
                System.out.println(book);
            }
        } else
            System.out.println("Книг не найдено!");
    }

    private void findAllAuthorBooks(BufferedReader reader) {
        Author author;
        try {
            System.out.println("Введите Id автора:");
            do {
                String id = reader.readLine();
                author = authorService.findById(id);
                if (author == null)
                    System.out.println("Ошибка! Укажите правильный Id!");
            }
            while (author == null);

            Book[] books = authorService.findAllAuthorBooks(author);
            System.out.println("=== Список книг автора " + author.getName() + " " + author.getLastName() + " ===");
            if (books != null && books.length != 0) {
                for (Book book : books) {
                }
            } else {
                System.out.println("Книг не найдено!");
            }
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }

    private Employee selectOrCreateDepartment(BufferedReader reader) {
        System.out.println("Департамент сотрудника:");
        System.out.println("1: Выбрать из списка департаментов");
        System.out.println("2: Создать новый департамент");
        String choice;
        Employee employee = new Employee();
        String id;
        try {
            do {
                choice = reader.readLine();
                switch (choice) {
                    case "1": {
                        System.out.println("Введите Id автора:");
                        do {
                            id = reader.readLine();
                            employee = employeeService.findByID(id);
                            if (employee == null && !id.equals("0"))
                                System.out.println("Ошибка! Введите правильный Id, или 0 для выхода:");
                        }
                        while (employee == null && !id.equals("0"));
                    }
                    break;
                    case "2":
                        employee = departmentService.create(reader);
                        break;
                }
            }
            while (!choice.equals("1") && !choice.equals("2"));

        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
        return author;
    }
}
