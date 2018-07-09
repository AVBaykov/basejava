package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;


public class ArrayStorage extends AbstractArrayStorage {

    protected Integer getKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insert(int key, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void remove(int key) {
        storage[key] = storage[size];
    }

}
