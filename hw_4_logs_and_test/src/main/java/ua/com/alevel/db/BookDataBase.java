package ua.com.alevel.db;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.Arrays;
import java.util.UUID;

public class BookDataBase {

    private static BookDataBase instance;
    private int size = 0;
    private static Book[] books;

    public BookDataBase() {
        books = new Book[size];
    }

    public static BookDataBase getInstance() {
        if (instance == null) {
            instance = new BookDataBase();
        }
        return instance;
    }

    private void increaseArrayBooks(Book book) {
        Book[] temporaryBooks = new Book[size + 1];
        if (size >= 0) System.arraycopy(books, 0, temporaryBooks, 0, size);
        size++;
        temporaryBooks[size - 1] = book;
        books = temporaryBooks;
    }

    public void create(Book book) {
        book.setId(generateId());
        increaseArrayBooks(book);
    }

    public void update(Book updateBook) {
        Book currentBook = findById(updateBook.getId());
        currentBook.setBookTitle(updateBook.getBookTitle());
        currentBook.setAuthor(updateBook.getAuthor());
    }

    public void delete(String id) {
        Book deleteBook = findById(id);
        int index = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId().equals(String.valueOf(deleteBook.getId()))) {
                books[i] = null;
                index = i;
            }
        }
        Book arrayBookDeleted[] = new Book[books.length - 1];
        for (int i = 0; i < index; i++) {
            arrayBookDeleted[i] = books[i];
        }
        for (int i = index; i < arrayBookDeleted.length; i++) {
            arrayBookDeleted[i] = books[i + 1];
        }
        books = Arrays.copyOf(arrayBookDeleted, books.length - 1);
        System.out.println("Книга была удалёна!" + "\n");
    }

    public Book findById(String id) {
        for (int i = 0; i < books.length; i++) {
            if (id.equals(String.valueOf(books[i].getId()))) {
                return books[i];
            }
        }
        return null;
    }

    public Book[] findAllBooks() {
        return books;
    }

    public Book[] findAllAuthorBooks(Author author) {
        Book[] temporaryArrayBooks = new Book[books.length];
        int i = 0;
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                temporaryArrayBooks[i] = book;
                i++;
            }
        }
        Book[] authorsBooks = new Book[i];
        System.arraycopy(temporaryArrayBooks, 0, authorsBooks, 0, authorsBooks.length);
        return authorsBooks;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}