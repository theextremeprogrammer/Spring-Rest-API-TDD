package com.example.restapitdd.books;

import com.example.restapitdd.books.Book;
import com.example.restapitdd.books.BooksRepository;
import com.example.restapitdd.books.NewBook;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public abstract class BooksRepositoryContractTestTemplate<BooksRepositoryType extends BooksRepository> {
    protected BooksRepositoryType booksRepository;

    protected abstract BooksRepositoryType createBooksRepository();

    @Before
    final public void setUp() {
        booksRepository = createBooksRepository();
    }

    @Test
    final public void fakeBooksRepo_whenNoBooksHaveBeenSaved_hasNoBooks() {
        assertThat(booksRepository.getAll().size(), equalTo(0));
    }

    @Test
    final public void fakeBooksRepo_whenBooksHaveBeenSaved_hasBooks() {
        booksRepository.create(new NewBook("", ""));


        assertThat(booksRepository.getAll().size(), greaterThan(0));
    }

    @Test
    final public void fakeBooksRepo_whenBooksHaveBeenSaved_returnsAllBooks() {
        booksRepository.create(new NewBook("TDD by Example", "Kent Beck"));
        booksRepository.create(new NewBook("Refactoring", "Martin Fowler"));


        List<Book> allBooks = booksRepository.getAll();


        assertThat(allBooks.get(0), equalTo(new Book(1, "TDD by Example", "Kent Beck")));
        assertThat(allBooks.get(1), equalTo(new Book(2, "Refactoring", "Martin Fowler")));
    }

    @Test
    final public void fakeBooksRepo_whenBooksHaveBeenSaved_returnsBookById() {
        booksRepository.create(new NewBook("TDD by Example", "Kent Beck"));
        booksRepository.create(new NewBook("Refactoring", "Martin Fowler"));


        Book actualBook = booksRepository.getById(2);


        assertThat(actualBook, equalTo(new Book(2, "Refactoring", "Martin Fowler")));
    }
}
