package com.example.restapitdd;

import java.util.List;

public class StubBooksRepository implements BooksRepository {
    private List<Book> getAll_returnValue;

    public void setGetAll_returnValue(List<Book> books) {
        getAll_returnValue = books;
    }

    @Override
    public List<Book> getAll() {
        return getAll_returnValue;
    }
}
