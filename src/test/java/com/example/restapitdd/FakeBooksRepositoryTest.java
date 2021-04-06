package com.example.restapitdd;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class FakeBooksRepositoryTest {
    private FakeBooksRepository fakeBooksRepository;

    @Before
    public void setUp() {
        fakeBooksRepository = new FakeBooksRepository();
    }

    @Test
    public void fakeBooksRepo_whenNoBooksHaveBeenSaved_hasNoBooks() {
        assertThat(fakeBooksRepository.getAll().size(), equalTo(0));
    }

    @Test
    public void fakeBooksRepo_whenBooksHaveBeenSaved_hasBooks() {
        fakeBooksRepository.create(new NewBook("", ""));


        assertThat(fakeBooksRepository.getAll().size(), greaterThan(0));
    }

    @Test
    public void fakeBooksRepo_whenBooksHaveBeenSaved_returnsAllBooks() {
        fakeBooksRepository.create(new NewBook("TDD by Example", "Kent Beck"));
        fakeBooksRepository.create(new NewBook("Refactoring", "Martin Fowler"));


        List<Book> allBooks = fakeBooksRepository.getAll();


        assertThat(allBooks.get(0), equalTo(new Book(1, "TDD by Example", "Kent Beck")));
        assertThat(allBooks.get(1), equalTo(new Book(2, "Refactoring", "Martin Fowler")));
    }

    @Test
    public void fakeBooksRepo_whenBooksHaveBeenSaved_returnsBookById() {
        fakeBooksRepository.create(new NewBook("TDD by Example", "Kent Beck"));
        fakeBooksRepository.create(new NewBook("Refactoring", "Martin Fowler"));


        Book actualBook = fakeBooksRepository.getById(2);


        assertThat(actualBook, equalTo(new Book(2, "Refactoring", "Martin Fowler")));
    }
}
