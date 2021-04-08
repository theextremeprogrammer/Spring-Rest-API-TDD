package com.example.restapitdd.books;

import com.example.restapitdd.books.Book;
import com.example.restapitdd.books.BooksRepository;
import com.example.restapitdd.books.NewBook;

import java.util.List;

public class SpyBooksRepository implements BooksRepository {
    @Override
    public List<Book> getAll() {
        return null;
    }

    private long get_argument_id;
    public long getGet_argument_id() {
        return get_argument_id;
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
}
