package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> storage = new ArrayList<>();

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doUpdate(Integer key, Resume resume) {
        storage.set(key, resume);
    }

    @Override
    protected boolean isExists(Integer key) {
        return key > -1;
    }

    @Override
    protected void doSave(Integer key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume doGet(Integer key) {
        return storage.get(key);
    }

    @Override
    protected void doDelete(Integer key) {
        storage.remove((int) key);
    }

    @Override
    public Stream<Resume> getStreamForSort() {
        return storage.stream();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
