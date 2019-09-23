package com.example.restapitdd;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BooksControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController())
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
        ;
    }
}
