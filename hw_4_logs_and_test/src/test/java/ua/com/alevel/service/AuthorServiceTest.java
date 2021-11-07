package ua.com.alevel.service;

import org.junit.Test;
import org.junit.jupiter.api.*;

import ua.com.alevel.entity.Author;

import java.util.Arrays;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorServiceTest {

    private final static AuthorService authorService = new AuthorService();
    private final static int AUTHORS_SIZE = 0;

    @Order(1)
    @Test
    public void shouldBeCreateAuthorWhenAuthorNameIsEmpty() {
        Author author = new Author();
        author.setName(null);
        author.setLastName("Noskov");
        authorService.create(author);

        verifyAuthorListWhenAuthorListIsNotEmpty(AUTHORS_SIZE + 1);
    }

    @Order(2)
    @Test
    public void shouldBeCreateAuthorWhenAuthorLastNameIsEmpty() {
        Author author = new Author();
        author.setName("Oleg");
        author.setLastName(null);
        authorService.create(author);

        verifyAuthorListWhenAuthorListIsNotEmpty(AUTHORS_SIZE + 2);
    }

    @Order(3)
    @Test
    public void shouldBeUpdateAuthor() {
        String id = getRandomIdFromAuthorArray();
        Author author = getRandomAuthorFromAuthorArray(id);
        author.setName("Other");
        author.setLastName("Other");
        authorService.update(author);
        author = authorService.findById(id);
        Assertions.assertEquals("Other", author.getName());
        Assertions.assertEquals("Other", author.getLastName());
    }

    @Order(4)
    @Test
    public void shouldBeFindAuthorWhen() {
        Author author = getRandomAuthorFromAuthorArray(getRandomIdFromAuthorArray());
        Assertions.assertNotNull(author);
    }

    @Order(5)
    @Test
    public void shouldBeDeleteAuthorWhenDeleteAuthor() {
        String id = getRandomIdFromAuthorArray();
        authorService.delete(id);
        verifyAuthorListWhenAuthorListIsNotEmpty(AUTHORS_SIZE + 1);
    }

    private void verifyAuthorListWhenAuthorListIsNotEmpty(int size) {
        Assertions.assertEquals(size, authorService.findAllAuthors().length);
    }

    private String getRandomIdFromAuthorArray() {
        return Arrays.stream(authorService.findAllAuthors()).findFirst().get().getId();
    }

    private Author getRandomAuthorFromAuthorArray(String id) {
        return authorService.findById(id);
    }
}