package com.example.restapitdd.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class RestControllerExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceError notFound(NotFoundException e) {
        return new ResourceError(e.getMessage());
    }
}
