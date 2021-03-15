package com.tv.sheild.datasource;

public class DbInitializer {

    public static InMemoryDataSourceImpl initializeInMemoryDatabase() {
        InMemoryDataSourceImpl inMemoryDataSourceImpl = new InMemoryDataSourceImpl();
        inMemoryDataSourceImpl.createCaptains();
        inMemoryDataSourceImpl.createAvengers();
        return inMemoryDataSourceImpl;
    }
}
