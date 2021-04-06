package com.example.restapitdd;

public class FakeBooksRepositoryTest extends BooksRepositoryContractTestTemplate<FakeBooksRepository> {
    @Override
    protected FakeBooksRepository createBooksRepository() {
        return new FakeBooksRepository();
    }
}
