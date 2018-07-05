package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected boolean isPresent(Object key) {
        return (int) key > -1;
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get((int) key);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove((int) key);
        ((ArrayList<Resume>) storage).trimToSize();
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        storage.add(resume);
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage.stream()
                .sorted(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid))
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        storage.set((int) key, resume);
    }
}
