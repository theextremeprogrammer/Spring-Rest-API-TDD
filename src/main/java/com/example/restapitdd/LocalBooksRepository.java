package com.example.restapitdd;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalBooksRepository implements BooksRepository {
    @Override
    public List<Book> getAll() {
        return null;
    }
}
