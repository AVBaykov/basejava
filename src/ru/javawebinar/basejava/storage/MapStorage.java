package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExists(Object key) {
        return key != null;
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object key) {
        return map.get(((Resume) key).getUuid());
    }

    @Override
    protected void doDelete(Object key) {
        map.remove(((Resume) key).getUuid());
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
