package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return map.values().stream()
                .sorted(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid))
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return map.size();
    }
}
