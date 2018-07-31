package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public interface SerializationStrategy {
    void doWrite(String resource, Resume resume);
    Resume doRead(String resource);
}
