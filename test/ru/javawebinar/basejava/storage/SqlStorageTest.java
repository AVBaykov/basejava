package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;

public class SqlStorageTest extends AbstractStorageTest{
    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getProperty("db.url"), Config.get().getProperty("db.user"),
                Config.get().getProperty("db.password")));
    }

}