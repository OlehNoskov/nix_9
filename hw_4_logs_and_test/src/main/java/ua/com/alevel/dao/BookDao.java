package ua.com.alevel.dao;

import ua.com.alevel.db.BookDataBase;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public class BookDao {
    public void create(Book book) {
        BookDataBase.getInstance().create(book);
    }

    public void update(Book book) {
        BookDataBase.getInstance().update(book);
    }

    public void delete(String id) {
        BookDataBase.getInstance().delete(id);
    }

    public Book findById(String id) {
        return BookDataBase.getInstance().findById(id);
    }

    public Book[] findAllBooks() {
        return BookDataBase.getInstance().findAllBooks();
    }
    public Book[] findAllAuthorBooks(Author author){
        return BookDataBase.getInstance().findAllAuthorBooks(author);
    }
}