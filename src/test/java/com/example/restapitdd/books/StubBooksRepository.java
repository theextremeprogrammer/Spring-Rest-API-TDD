package com.example.restapitdd.books;

import java.util.List;

public class StubBooksRepository implements BooksRepository {
    private List<Book> getAll_returnValue;
    private NotFoundException getById_throwsException;
    private Book get_returnValue;

    public void setGetAll_returnValue(List<Book> books) {
        getAll_returnValue = books;
    }

    @Override
    public List<Book> getAll() {
        return getAll_returnValue;
    }

    public void setGet_returnValue(Book book) {
        get_returnValue = book;
    }

    public void set_getById_throwsException(NotFoundException exception) {
        getById_throwsException = exception;
    }

    @Override
    public Book getById(long id) {
        if (getById_throwsException != null) {
            throw getById_throwsException;
        }

        return get_returnValue;
    }

    @Override
    public Book create(NewBook newBook) {
        return null;
    }
}
