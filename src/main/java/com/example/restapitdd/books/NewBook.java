package com.example.restapitdd.books;

import java.util.Objects;

public class NewBook {
    private final String name;
    private final String author;

    public NewBook(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewBook newBook = (NewBook) o;
        return Objects.equals(name, newBook.name) &&
                Objects.equals(author, newBook.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author);
    }

    @Override
    public String toString() {
        return "NewBook{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
