package ua.com.alevel.dao;

import ua.com.alevel.db.AuthorDataBase;
import ua.com.alevel.db.BookDataBase;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public class AuthorDao {

    public void create(Author author) {
        AuthorDataBase.getInstance().create(author);
    }

    public void update(Author author) {
        AuthorDataBase.getInstance().update(author);
    }

    public void delete(String id) {
        AuthorDataBase.getInstance().delete(id);
    }

    public Author findById(String id) {
        return AuthorDataBase.getInstance().findById(id);
    }

    public Author[] findAllAuthors() {
        return AuthorDataBase.getInstance().findAllAuthors();
    }

    public Book[] findAllBooksAuthor(Author author) {
       return BookDataBase.getInstance().findAllAuthorBooks(author);
    }
}