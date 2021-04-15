package com.example.restapitdd.books;

public class NotFoundException extends RuntimeException {
    private String message;

    public NotFoundException(String message) {
        super(message);
    }
}
