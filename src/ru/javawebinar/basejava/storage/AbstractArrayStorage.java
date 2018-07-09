package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract void insert(int key, Resume resume);

    protected abstract void remove(int key);

    @Override
    protected void doUpdate(Integer key, Resume resume) {
        storage[key] = resume;
    }

    @Override
    protected boolean isExists(Integer key) {
        return key > -1;
    }

    @Override
    protected final void doSave(Integer key, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insert(key, resume);
        size++;
    }

    @Override
    protected Resume doGet(Integer key) {
        return storage[key];
    }

    @Override
    protected final void doDelete(Integer key) {
        size--;
        remove(key);
        storage[size] = null;
    }

    @Override
    public Stream<Resume> getStreamForSort() {
        return Arrays.stream(Arrays.copyOf(storage, size));
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }
}
