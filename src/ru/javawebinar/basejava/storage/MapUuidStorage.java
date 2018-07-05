package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected boolean isPresent(Object key) {
        return false;
    }

    @Override
    protected Resume doGet(Object key) {
        return null;
    }

    @Override
    protected void doDelete(Object key) {

    }

    @Override
    protected void doSave(Object key, Resume resume) {

    }

    @Override
    protected Object getKey(String uuid) {
        return null;
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.asList(new Resume[0]);
    }

    @Override
    public int size() {
        return 0;
    }
}
