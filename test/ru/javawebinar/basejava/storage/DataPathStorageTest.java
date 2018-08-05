package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.DataSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new DataSerializer()));
    }
}