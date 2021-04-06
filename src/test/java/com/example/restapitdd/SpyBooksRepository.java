package com.example.restapitdd;

import java.util.List;

public class SpyBooksRepository implements BooksRepository {
    private long get_argument_id;

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getById(long id) {
        get_argument_id = id;
        return null;
    }

    public long getGet_argument_id() {
        return get_argument_id;
    }
}
