package com.example.restapitdd.books;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalBooksRepository implements BooksRepository {
    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getById(long id) {
        return null;
    }

    @Override
    public Book create(NewBook newBook) {
        return null;
    }
}
