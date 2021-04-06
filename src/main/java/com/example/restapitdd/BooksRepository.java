package com.example.restapitdd;

import java.util.List;

public interface BooksRepository {
    List<Book> getAll();

    Book get(long id);
}
