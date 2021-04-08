package com.example.restapitdd.books;

import com.example.restapitdd.books.Book;
import com.example.restapitdd.books.BooksRepository;
import com.example.restapitdd.books.NewBook;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MockBooksRepository implements BooksRepository {
    private Long get_argument_id;

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getById(long id) {
        get_argument_id = id;
        return null;
    }

    @Override
    public Book create(NewBook newBook) {
        return null;
    }

    public void verify_getById(long expectedId) {
        assertThat(get_argument_id, equalTo(expectedId));
    }
}
