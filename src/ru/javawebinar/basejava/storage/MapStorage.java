package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    protected Resume getResumeByIndex(int index) {
        return null;
    }

    @Override
    protected void deleteResume(int index) {

    }

    @Override
    protected void saveResume(Resume resume, int index) {

    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    protected boolean isOverflow() {
        return false;
    }

    @Override
    protected void rewrite(Resume resume, int index) {

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
