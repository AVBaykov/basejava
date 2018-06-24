package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    protected boolean isPresent(Object key) {
        return false;
    }

    @Override
    protected Resume getResume(Object key) {
        return null;
    }

    @Override
    protected void deleteResume(Object key) {

    }

    @Override
    protected void saveResume(Object key, Resume resume) {

    }

    @Override
    protected Object getKey(String uuid) {
        return null;
    }

    @Override
    protected void rewrite(Object key, Resume resume) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
