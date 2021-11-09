package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BookController {
    public final BookService bookService = new BookService();
    private final AuthorService authorService = new AuthorService();
    private final AuthorController authorController = new AuthorController();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите, что будете делать:");
        String position;
        try {
            showMenuBook();
            while ((position = reader.readLine()) != null && !position.equals("0")) {
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }

    private void showMenuBook() {
        System.out.println();
        System.out.println("===== МЕНЮ КНИГИ =====");
        System.out.println("Нажмите 1: Создание новой книги");
        System.out.println("Нажмите 2: Изменения данных книги");
        System.out.println("Нажмите 3: Удаление книги");
        System.out.println("Нажмите 4: Поиска книги по Id");
        System.out.println("Нажмите 5: Поиск всех книг");
        System.out.println("Нажмите 6: Поиск книги по указанному автору");
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
        showMenuBook();
    }

    private void create(BufferedReader reader) {
        System.out.println("Введите название новой книги:");
        try {
            String name = reader.readLine();
            if (!name.isEmpty()) {
                Author author = selectOrCreateAuthor(reader);
                if (author == null) {
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

    private Author selectOrCreateAuthor(BufferedReader reader) {
        System.out.println("Автор книги:");
        System.out.println("1: Выбрать из списка авторов");
        System.out.println("2: Создать нового автора");
        String choice;
        Author author = new Author();
        String id;
        try {
            do {
                choice = reader.readLine();
                switch (choice) {
                    case "1": {
                        System.out.println("Введите Id автора:");
                        do {
                            id = reader.readLine();
                            author = authorService.findById(id);
                            if (author == null && !id.equals("0"))
                                System.out.println("Ошибка! Введите правильный Id, или 0 для выхода:");
                        }
                        while (author == null && !id.equals("0"));
                    }
                    break;
                    case "2":
                        author = authorController.create(reader);
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