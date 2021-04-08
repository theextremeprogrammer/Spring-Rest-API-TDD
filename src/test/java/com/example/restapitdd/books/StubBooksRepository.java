package com.example.restapitdd.books;

import com.example.restapitdd.books.Book;
import com.example.restapitdd.books.BooksRepository;
import com.example.restapitdd.books.NewBook;

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

    private Book get_returnValue;
    public void setGet_returnValue(Book book) {
        get_returnValue = book;
    }

    @Override
    public Book getById(long id) {
        return get_returnValue;
    }

    @Override
    public Book create(NewBook newBook) {
        return null;
    }
}
