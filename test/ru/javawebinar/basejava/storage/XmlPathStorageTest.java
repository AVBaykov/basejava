package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.XmlSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new XmlSerializer()));
    }
}