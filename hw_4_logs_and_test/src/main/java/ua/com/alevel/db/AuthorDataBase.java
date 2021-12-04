package ua.com.alevel.db;

import ua.com.alevel.persistence.entity.Author;

import java.util.Arrays;
import java.util.UUID;

public class AuthorDataBase {

    private static AuthorDataBase instance;
    private Author[] authors;
    private static int DEFAULT_SIZE = 0;

    private AuthorDataBase() {
        authors = new Author[DEFAULT_SIZE];
    }

    private void increaseArrayAuthors(Author author) {
        Author[] temporaryAuthors = new Author[DEFAULT_SIZE + 1];
        if (DEFAULT_SIZE >= 0) System.arraycopy(authors, 0, temporaryAuthors, 0, DEFAULT_SIZE);
        DEFAULT_SIZE++;
        temporaryAuthors[DEFAULT_SIZE - 1] = author;
        authors = temporaryAuthors;
    }

    public static AuthorDataBase getInstance() {
        if (instance == null) {
            instance = new AuthorDataBase();
        }
        return instance;
    }

    public void create(Author author) {
        author.setId(generateId());
        increaseArrayAuthors(author);
    }

    public void update(Author author) {
        Author currentAuthor = findById(author.getId());
        currentAuthor.setName(author.getName());
        currentAuthor.setLastName(author.getLastName());
    }

    public void delete(String id) {
        Author deleteUser = findById(id);
        int index = -1;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getId().equals(String.valueOf(deleteUser.getId()))) {
                authors[i] = null;
                index = i;
            }
        }
        Author arrayAuthorDeleted[] = new Author[authors.length - 1];
        for (int i = 0; i < index; i++) {
            arrayAuthorDeleted[i] = authors[i];
        }
        for (int i = index; i < arrayAuthorDeleted.length; i++) {
            arrayAuthorDeleted[i] = authors[i + 1];
        }
        authors = Arrays.copyOf(arrayAuthorDeleted, authors.length - 1);
    }

    public Author findById(String id) {
        for (Author author : authors) {
            if (author.getId().equals(id))
                return author;
        }
        return null;
    }

    public Author[] findAllAuthors() {
        return authors;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}