package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected boolean isResumePresent(Object key) {
        return (Integer) key >= 0;
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove((int) key);
        ((ArrayList<Resume>) storage).trimToSize();
    }

    @Override
    protected void saveResume(Object key, Resume resume) {
        storage.add(resume);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
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
    protected void rewrite(Object key, Resume resume) {
        storage.set((Integer) key, resume);
    }
}
