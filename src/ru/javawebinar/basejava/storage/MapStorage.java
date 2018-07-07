package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapStorage extends AbstractStorage {

    private Map<Integer, Resume> map = new HashMap<>();

    @Override
    protected Integer getKey(String uuid) {
        return uuid.hashCode();
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        map.put((Integer) key, resume);
    }

    @Override
    protected boolean isExists(Object key) {
        return map.containsKey(key);
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        map.put((Integer) key, resume);
    }

    @Override
    protected Resume doGet(Object key) {
        return map.get(key);
    }

    @Override
    protected void doDelete(Object key) {
        map.remove(key);
    }

    @Override
    public Stream<Resume> getStreamForSort() {
        return map.values().stream();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}
