package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract void insert(int key, Resume resume);

    protected abstract void remove(int key);

    @Override
    protected void doUpdate(Object key, Resume resume) {
        storage[(int) key] = resume;
    }

    @Override
    protected boolean isExists(Object key) {
        return (int) key > -1;
    }

    @Override
    protected final void doSave(Object key, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insert((int) key, resume);
        size++;
    }

    @Override
    protected Resume doGet(Object key) {
        return storage[(int) key];
    }

    @Override
    protected final void doDelete(Object key) {
        size--;
        remove((int) key);
        storage[size] = null;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.stream(Arrays.copyOf(storage, size))
                .sorted(Comparator.comparing(Resume::getFullName)
                        .thenComparing(Resume::getUuid))
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return size;
    }
}
