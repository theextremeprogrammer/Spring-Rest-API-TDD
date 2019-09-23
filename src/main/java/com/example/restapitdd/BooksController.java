package com.example.restapitdd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    @GetMapping("/hardcoded")
    public List<String> getBooksHardcoded() {
        return new ArrayList<>();
    }
}
