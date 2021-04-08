package com.example.restapitdd.books;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
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
                .andExpect(jsonPath("$[0].id", equalTo(1)))
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
                singletonList(new Book(1L, "Clean Code", "Robert Martin"))
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
                .thenReturn(singletonList(new Book(1L, "Clean Code", "Robert Martin")));


        mockMvc.perform(get("/api/books/dynamic"))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
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
                new Book(1L, "Refactoring", "Martin Fowler")
        );

        mockMvc.perform(get("/api/books/dynamic/1"))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Refactoring")))
                .andExpect(jsonPath("$.author", equalTo("Martin Fowler")))
        ;
    }

    @Test
    public void getBook_passesDataToRepository_usingSpy() throws Exception {
        SpyBooksRepository spyBooksRepository = new SpyBooksRepository();
        BooksController booksController = new BooksController(spyBooksRepository);

        mockMvc = MockMvcBuilders
                .standaloneSetup(booksController)
                .build();


        mockMvc.perform(get("/api/books/dynamic/999"));


        assertThat(spyBooksRepository.getGet_argument_id(), equalTo(999L));
    }

    @Test
    public void getBook_passesDataToRepository_usingTrueMock() throws Exception {
        MockBooksRepository trueMockBooksRepository = new MockBooksRepository();
        BooksController booksController = new BooksController(trueMockBooksRepository);

        mockMvc = MockMvcBuilders
                .standaloneSetup(booksController)
                .build();


        mockMvc.perform(get("/api/books/dynamic/999"));


        trueMockBooksRepository.verify_getById(999L);
    }

    @Test
    public void getBook_passesDataToRepository_mockito() throws Exception {
        BooksRepository mockBooksRepository = mock(BooksRepository.class);
        BooksController booksController = new BooksController(mockBooksRepository);

        mockMvc = MockMvcBuilders
                .standaloneSetup(booksController)
                .build();


        mockMvc.perform(get("/api/books/dynamic/999"));


        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(mockBooksRepository, times(1)).getById(longArgumentCaptor.capture());

        assertThat(longArgumentCaptor.getValue(), equalTo(999L));
    }

    @Test
    public void getBooks_returnsBooks_usingFake() throws Exception {
        FakeBooksRepository fakeBooksRepository = new FakeBooksRepository();
        BooksController booksController = new BooksController(fakeBooksRepository);

        fakeBooksRepository.create(new NewBook("TDD by Example", "Kent Beck"));
        fakeBooksRepository.create(new NewBook("Refactoring", "Martin Fowler"));

        mockMvc = MockMvcBuilders
                .standaloneSetup(booksController)
                .build();


        mockMvc.perform(get("/api/books/dynamic"))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("TDD by Example")))
                .andExpect(jsonPath("$[0].author", equalTo("Kent Beck")))

                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[1].name", equalTo("Refactoring")))
                .andExpect(jsonPath("$[1].author", equalTo("Martin Fowler")))
        ;
    }

    @Test
    public void getBook_usingFake() throws Exception {
        FakeBooksRepository fakeBooksRepository = new FakeBooksRepository();
        BooksController booksController = new BooksController(fakeBooksRepository);

        Book refactoringBook = fakeBooksRepository.create(new NewBook("Refactoring", "Martin Fowler"));

        mockMvc = MockMvcBuilders
                .standaloneSetup(booksController)
                .build();


        mockMvc.perform(get("/api/books/dynamic/1"))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Refactoring")))
                .andExpect(jsonPath("$.author", equalTo("Martin Fowler")))
        ;
    }
}
