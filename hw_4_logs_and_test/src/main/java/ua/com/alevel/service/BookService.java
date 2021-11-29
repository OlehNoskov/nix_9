package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.db.BookDataBase;
import ua.com.alevel.entity.Book;

public class BookService {

    private final BookDataBase bookDataBase = new BookDataBase();
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public void create(Book book) {
        LOGGER_INFO.info("Book start created");
        bookDataBase.create(book);
        LOGGER_INFO.info("Book start created");

    }

    public void update(Book book) {
        LOGGER_WARN.warn("Book start update");
        bookDataBase.update(book);
        LOGGER_WARN.warn("Book start update");
    }

    public boolean delete(String id) {
        LOGGER_INFO.warn("Book start deleted");
        bookDataBase.delete(id);
        return false;
    }

    public Book findById(String id) {
        try {
            return bookDataBase.findById(id);
        } catch (RuntimeException runtimeException) {
            LOGGER_ERROR.error("Book not found by Id " + id);
        }
        return bookDataBase.findById(id);
    }

    public Book[] findAllBooks() {
        return bookDataBase.findAllBooks();
    }
}