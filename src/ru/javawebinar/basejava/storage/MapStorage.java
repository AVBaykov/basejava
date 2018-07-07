package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    private String getUuidFromKey(Resume resume) {
        return resume.getUuid();
    }

    @Override
    protected Resume getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        map.put(getUuidFromKey((Resume) key), resume);
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
        return map.get(getUuidFromKey((Resume) key));
    }

    @Override
    protected void doDelete(Object key) {
        map.remove(getUuidFromKey((Resume) key));
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
