package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;

import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class AuthorController {
    private final AuthorService authorService = new AuthorService();
    private final BookService bookService = new BookService();

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
        System.out.println("Нажмите 1: Создание нового автора");
        System.out.println("Нажмите 2: Изменение данных существующего автора");
        System.out.println("Нажмите 3: Удаления автора");
        System.out.println("Нажмите 4: Поиска автора по Id");
        System.out.println("Нажмите 5: Поиск списка всех авторов");
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
                BaseController.run();
                break;
        }
        showMenuProgramAuthor();
    }

    public Author create(BufferedReader reader) {
        Author author = new Author();
        try {
            System.out.println("=== Создание автора ===");
            System.out.println("Введите имя автора:");
            String name = reader.readLine();
            System.out.println("Введите Фамилию автора:");
            String lastname = reader.readLine();
            if (!name.isEmpty() && !lastname.isEmpty()) {
                author.setName(name);
                author.setLastName(lastname);
                authorService.create(author);
            } else {
                System.out.println("Автор не создан!");
                System.out.println("Вы не ввели имя или фамилию автора!");
            }
            return author;
        } catch (NumberFormatException e) {
            System.out.println("Введены некорректные данные автора!");
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
        return author;
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("=== Обновление данных автора ===");
        System.out.println("Укажите Id автора для обновления данных:");
        String idAuthor = reader.readLine();
        Author author = authorService.findById(idAuthor);
        if (author == null) {
            System.out.println("Введен некорректный Id!!!");
            return;
        } else {
            System.out.println("Укажите новое имя автора:");
            String name = reader.readLine();
            System.out.println("Укажите новую фамилию автора:");
            String lastname = reader.readLine();
            author.setName(name);
            author.setLastName(lastname);
            authorService.update(author);
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("=== Удаление автора ===");
            System.out.println("Введите Id автора:");
            Author author;
            String idAuthor = reader.readLine();
            author = authorService.findById(idAuthor);
            if (author == null) {
                System.out.println("Id не был введен корректно");
                return;
            }
            Book[] books = authorService.findAllAuthorBooks(author);
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