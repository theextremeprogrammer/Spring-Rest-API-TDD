package com.example.restapitdd.books;

import java.util.List;

public interface BooksRepository {
    List<Book> getAll();

    Book getById(long id);

    Book create(NewBook newBook);
}
