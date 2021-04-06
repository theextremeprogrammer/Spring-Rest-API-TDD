package com.example.restapitdd;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
    public void getBooks_returnsOkHttpStatus() throws Exception {
        mockMvc.perform(get("/api/books/hardcoded"))
                .andExpect(status().isOk());
    }

    @Test
    public void getBooks_returnsASingleBook() throws Exception {
        mockMvc.perform(get("/api/books/hardcoded"))
                .andExpect(jsonPath("$[0].name", equalTo("TDD by Example")))
                .andExpect(jsonPath("$[0].author", equalTo("Kent Beck")))
        ;
    }

    @Test
    public void getBooks_returnsOkHttpStatus_dynamic() throws Exception {
        mockMvc.perform(get("/api/books/dynamic"))
                .andExpect(status().isOk());
    }

    @Test
    public void getBooks_returnsASingleBook_dynamic() throws Exception {
        stubBooksRepository.setGetAll_returnValue(
                singletonList(new Book("Clean Code", "Robert Martin"))
        );

        mockMvc.perform(get("/api/books/dynamic"))
                .andExpect(jsonPath("$[0].name", equalTo("Clean Code")))
                .andExpect(jsonPath("$[0].author", equalTo("Robert Martin")))
        ;
    }

    @Test
    public void getBooks_returnsASingleBook_dynamic_usingMockito() throws Exception {
        BooksRepository mockBooksRepository = mock(BooksRepository.class);
        BooksController booksController = new BooksController(mockBooksRepository);

        mockMvc = MockMvcBuilders
                .standaloneSetup(booksController)
                .build();

        when(mockBooksRepository.getAll())
                .thenReturn(singletonList(new Book("Clean Code", "Robert Martin")));


        mockMvc.perform(get("/api/books/dynamic"))
                .andExpect(jsonPath("$[0].name", equalTo("Clean Code")))
                .andExpect(jsonPath("$[0].author", equalTo("Robert Martin")))
        ;
    }

    @Test
    public void getBook_returnsOkHttpStatus_dynamic() throws Exception {
        mockMvc.perform(get("/api/books/dynamic/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getBook_returnsASingleBook_dynamic() throws Exception {
        stubBooksRepository.setGet_returnValue(
                new Book("Refactoring", "Martin Fowler")
        );

        mockMvc.perform(get("/api/books/dynamic/1"))
                .andExpect(jsonPath("$.name", equalTo("Refactoring")))
                .andExpect(jsonPath("$.author", equalTo("Martin Fowler")))
        ;
    }

    @Test
    public void getBook_passesDataToRepository() throws Exception {
        SpyBooksRepository spyBooksRepository = new SpyBooksRepository();
        BooksController booksController = new BooksController(spyBooksRepository);

        mockMvc = MockMvcBuilders
                .standaloneSetup(booksController)
                .build();


        mockMvc.perform(get("/api/books/dynamic/999"));


        assertThat(spyBooksRepository.getGet_argument_id(), equalTo(999L));
    }
}
