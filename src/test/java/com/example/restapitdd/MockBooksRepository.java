package com.example.restapitdd;

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

    public void verify_getById(long expectedId) {
        assertThat(get_argument_id, equalTo(expectedId));
    }
}
