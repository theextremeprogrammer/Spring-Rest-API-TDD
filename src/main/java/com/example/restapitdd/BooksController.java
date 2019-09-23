package com.example.restapitdd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.singletonList;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private BooksRepository booksRepository;

    public BooksController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @GetMapping("/hardcoded")
    public List<Book> getBooksHardcoded() {
        Book tddBook = new Book("TDD by Example", "Kent Beck");
        return singletonList(tddBook);
    }

    @GetMapping("/dynamic")
    public List<Book> getBooksDynamic() {
        return booksRepository.getAll();
    }
}















