package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategies.ObjectStreamStrategy;

public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new ObjectStreamStrategy()));
    }
}