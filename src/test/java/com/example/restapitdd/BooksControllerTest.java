package com.example.restapitdd;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BooksControllerTest {
    private MockMvc mockMvc;
    private StubBooksRepository stubBooksRepository;

    @Before
    public void setUp() throws Exception {
        stubBooksRepository = new StubBooksRepository();
        BooksController booksController = new BooksController(stubBooksRepository);

        mockMvc = MockMvcBuilders
                .standaloneSetup(booksController)
                .build();
    }

    @Test
    public void test_getBooksController_returnsOkHttpStatus() throws Exception {
        mockMvc.perform(get("/api/books/hardcoded"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getBooksController_returnsASingleBook() throws Exception {
        mockMvc.perform(get("/api/books/hardcoded"))
                .andExpect(jsonPath("$[0].name", equalTo("TDD by Example")))
                .andExpect(jsonPath("$[0].author", equalTo("Kent Beck")))
        ;
    }

    @Test
    public void test_getBooksController_returnsOkHttpStatus_dynamic() throws Exception {
        mockMvc.perform(get("/api/books/dynamic"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getBooksController_returnsASingleBook_dynamic() throws Exception {
        stubBooksRepository.setGetAll_returnValue(
                Collections.singletonList(new Book("Refactoring", "Martin Fowler"))
        );

        mockMvc.perform(get("/api/books/dynamic"))
                .andExpect(jsonPath("$[0].name", equalTo("Refactoring")))
                .andExpect(jsonPath("$[0].author", equalTo("Martin Fowler")))
        ;
    }

    // TDD Approach: Triangulation
    @Test
    public void test_getBooksController_returnsADifferentBook_dynamic() throws Exception {
        stubBooksRepository.setGetAll_returnValue(
                Collections.singletonList(new Book("Clean Code", "Robert Martin"))
        );

        mockMvc.perform(get("/api/books/dynamic"))
                .andExpect(jsonPath("$[0].name", equalTo("Clean Code")))
                .andExpect(jsonPath("$[0].author", equalTo("Robert Martin")))
        ;
    }
}
