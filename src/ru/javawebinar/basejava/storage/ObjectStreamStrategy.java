package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ObjectStreamStrategy implements SerializationStrategy {

    @Override
    public void doWrite(String resource, Resume resume) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(resource)))) {
            oos.writeObject(resume);
        } catch (IOException e) {
            throw new StorageException("Resume write error", null, e);
        }
    }

    @Override
    public Resume doRead(String resource) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(resource)))) {
            return (Resume) ois.readObject();
        } catch (Exception e) {
            throw new StorageException("Resume read error", null, e);
        }
    }
}
