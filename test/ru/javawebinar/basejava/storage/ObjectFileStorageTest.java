package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategies.ObjectStreamStrategy;

public class ObjectFileStorageTest extends AbstractStorageTest {
    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStrategy()));
    }
}