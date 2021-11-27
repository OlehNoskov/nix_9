package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.DepartmentController;
import ua.com.alevel.entity.Department;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.impl.DepartmentServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DepartmentControllerImpl implements DepartmentController {

    DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        try {
            showMenuProgramAuthor();
            while ((choice = reader.readLine()) != null) {
                crud(choice, reader);
            }
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }

    private void showMenuProgramAuthor() {
        System.out.println();
        System.out.println("===== МЕНЮ АВТОРА =====");
        System.out.println("Нажмите 1: Создание нового департамента");
        System.out.println("Нажмите 2: Изменение название департамента");
        System.out.println("Нажмите 3: Удаления департамента");
        System.out.println("Нажмите 4: Поиска департамента по Id");
        System.out.println("Нажмите 5: Поиск списка всех департаментов");
        System.out.println("Нажмите 0: Выход в Главное Меню");
    }

    private void crud(String choice, BufferedReader reader) throws IOException {
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
                findAllAuthors();
                break;
            case "0":
                new BaseControllerImpl().run();
                break;
        }
        showMenuProgramAuthor();
    }

    public Department create(BufferedReader reader) {
        Department department = new Department();
        try {
            System.out.println("=== Создание автора ===");
            System.out.println("Введите название департамента:");
            String name = reader.readLine();
            if (!name.isEmpty()) {
                department.setDepartmentName(name);
                departmentService.create(department);
            } else {
                System.out.println("Департамент не создан!");
                System.out.println("Вы не ввели название департамента!");
            }
            return department;
        } catch (NumberFormatException e) {
            System.out.println("Введены некорректные данные автора!");
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
        return department;
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("=== Изменение название департамента ===");
        System.out.println("Укажите Id департамента:");
        String idDepartment = reader.readLine();
        Department department = departmentService.findByID(idDepartment);
        if (department == null) {
            System.out.println("Введен некорректный Id!!!");
            return;
        } else {
            System.out.println("Укажите новое название департамента:");
            String name = reader.readLine();
            department.setDepartmentName(name);
            departmentService.update(department);
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("=== Удаление департамента ===");
            System.out.println("Введите Id:");
            Department department;
            String idDepartment = reader.readLine();
            department = departmentService.findByID(idDepartment);
            if (department == null) {
                System.out.println("Id не был введен корректно");
                return;
            }
            List<Department> departments = departmentService.findByID(idDepartment);
            for (Book book : books) {
                bookService.delete(book.getId());
            }
            authorService.delete(author.getId());
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный id!");
        }
    }

    private void findById(BufferedReader reader) throws IOException {
        System.out.println("=== Поиск автора ===");
        System.out.println("Укажите Id автора, которого хотите найти:");
        Author author;
        String idAuthor = reader.readLine();
        author = authorService.findById(idAuthor);
        if (author == null) {
            System.out.println("Id не был введен корректно");
            return;
        }
        System.out.println(author);
    }

    private void findAllAuthors() {
        System.out.println("=== Поиск авторов ===");
        Author[] authors = authorService.findAllAuthors();
        if (authors != null && authors.length != 0) {
            for (Author author : authors) {
                System.out.println(author);
            }
        } else {
            System.out.println("Авторов не найдено!");
        }
    }
}