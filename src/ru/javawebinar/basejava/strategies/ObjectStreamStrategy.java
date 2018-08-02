package ru.javawebinar.basejava.strategies;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamStrategy implements SerializationStrategy {

    @Override
    public void doWrite(OutputStream os, Resume resume) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(os))) {
            oos.writeObject(resume);
        } catch (IOException e) {
            throw new StorageException("Resume write error", null, e);
        }
    }

    @Override
    public Resume doRead(InputStream is) {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(is))) {
            return (Resume) ois.readObject();
        } catch (Exception e) {
            throw new StorageException("Resume read error", null, e);
        }
    }
}
