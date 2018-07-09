package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapUuidStorage extends AbstractStorage<String> {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(String key, Resume resume) {
        map.put(key, resume);
    }

    @Override
    protected boolean isExists(String key) {
        return map.containsKey(key);
    }

    @Override
    protected Resume doGet(String key) {
        return map.get(key);
    }

    @Override
    protected void doDelete(String key) {
        map.remove(key);
    }

    @Override
    protected void doSave(String key, Resume resume) {
        map.put(key, resume);
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
