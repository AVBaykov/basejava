package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    private static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;


    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getResume(Object key) {
        return storage[(int) key];
    }

    @Override
    protected void deleteResume(Object key) {
        size--;
        remove((Integer) key);
        storage[size] = null;
    }

    @Override
    protected void saveResume(Object key, Resume resume) {
        insert((Integer) key, resume);
        size++;
    }

    @Override
    protected boolean isResumePresent(Object key) {
        return (Integer) key >= 0;
    }

    @Override
    protected void existStorageExceptionThrower(Object key, String uuid) {
        super.existStorageExceptionThrower(key, uuid);
        if (size == STORAGE_LIMIT) throw new StorageException("Storage overflow", uuid);
    }

    @Override
    protected void rewrite(Object key, Resume resume) {
        storage[(int) key] = resume;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insert(Integer key, Resume resume);

    protected abstract void remove(Integer key);
}
