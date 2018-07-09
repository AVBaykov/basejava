package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Resume key, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExists(Resume key) {
        return key != null;
    }

    @Override
    protected void doSave(Resume key, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume key) {
        return key;
    }

    @Override
    protected void doDelete(Resume key) {
        map.remove(key.getUuid());
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
