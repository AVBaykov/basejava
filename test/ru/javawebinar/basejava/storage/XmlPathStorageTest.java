package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategies.XmlStrategy;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new XmlStrategy()));
    }
}