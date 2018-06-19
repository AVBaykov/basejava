package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;


    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getResumeByIndex(int index) {
        return storage[index];
    }

    @Override
    protected void deleteResume(int index) {
        size--;
        remove(index);
        storage[size] = null;
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        insert(resume, index);
        size++;
    }

    @Override
    protected boolean isOverflow() {
        return size == STORAGE_LIMIT;
    }

    @Override
    protected void rewrite(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insert(Resume resume, int index);

    protected abstract void remove(int index);
}
