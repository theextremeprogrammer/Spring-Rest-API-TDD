package com.example.restapitdd.books;

public class FakeBooksRepositoryTest extends BooksRepositoryContractTestTemplate<FakeBooksRepository> {
    @Override
    protected FakeBooksRepository createBooksRepository() {
        return new FakeBooksRepository();
    }
}
