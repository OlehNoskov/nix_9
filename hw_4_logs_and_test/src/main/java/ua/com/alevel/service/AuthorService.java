package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public class AuthorService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final AuthorDao authorDao = new AuthorDao();

    public void create(Author author) {
        LOGGER_INFO.info("Author start created");
        authorDao.create(author);
        LOGGER_INFO.info("Author finish created");
    }

    public void update(Author author) {
        LOGGER_WARN.warn("Author start update");
        authorDao.update(author);
        LOGGER_WARN.warn("Author finish update");
    }

    public void delete(String id) {
        LOGGER_INFO.warn("Author start deleted");
        authorDao.delete(id);
        LOGGER_INFO.warn("Author finish deleted");
    }

    public Author findById(String id) {
        try {
            return authorDao.findById(id);
        }catch (RuntimeException e){
            LOGGER_ERROR.error("Author not found by Id "+ id);
        }
        return authorDao.findById(id);
    }

    public Author[] findAllAuthors() {
        return authorDao.findAllAuthors();
    }

    public Book[] findAllAuthorBooks(Author author) {
        return authorDao.findAllBooksAuthor(author);
    }
}