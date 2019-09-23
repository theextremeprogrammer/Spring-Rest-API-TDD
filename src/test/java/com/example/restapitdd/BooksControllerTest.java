package com.example.restapitdd;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BooksControllerTest {
    @Test
    public void test_getBooksController_returnsOkHttpStatus() throws Exception {
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController())
                .build();


        mockMvc.perform(get("/api/books/hardcoded"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getBooksController_returnsAnEmptyList() throws Exception {
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController())
                .build();


        mockMvc.perform(get("/api/books/hardcoded"))
                .andExpect(jsonPath("$", hasSize(0)));
    }


}
