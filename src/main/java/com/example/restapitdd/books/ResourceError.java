package com.example.restapitdd.books;

public class ResourceError {
    private String errorMessage;

    public ResourceError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
