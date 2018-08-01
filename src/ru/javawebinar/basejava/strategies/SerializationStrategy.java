package ru.javawebinar.basejava.strategies;

import ru.javawebinar.basejava.model.Resume;

import java.io.InputStream;
import java.io.OutputStream;

public interface SerializationStrategy {
    void doWrite(OutputStream os, Resume resume);

    Resume doRead(InputStream is);
}
