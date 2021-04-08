package com.example.restapitdd.books;

import org.springframework.web.bind.annotation.*;

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
        Book tddBook = new Book(1L, "TDD by Example", "Kent Beck");
        return singletonList(tddBook);
    }

    @GetMapping("/dynamic")
    public List<Book> getBooksDynamic() {
        return booksRepository.getAll();
    }

    @GetMapping("/dynamic/{id}")
    public Book getBooksDynamic(@PathVariable long id) {
        return booksRepository.getById(id);
    }
}















