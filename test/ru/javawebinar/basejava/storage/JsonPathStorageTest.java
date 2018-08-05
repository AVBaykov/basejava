package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.JsonSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new JsonSerializer()));
    }
}