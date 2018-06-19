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
    protected Resume getResumeByIndex(int index) {
        return storage.get(index);
    }

    @Override
    protected void deleteResume(int index) {
        storage.remove(index);
        if (storage instanceof ArrayList) {
            ((ArrayList<Resume>) storage).trimToSize();
        }
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[storage.size()];
        return storage.toArray(resumes);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }


    @Override
    protected boolean isOverflow() {
        return size() == STORAGE_LIMIT;
    }

    @Override
    protected void rewrite(Resume resume, int index) {
        storage.set(index, resume);
    }
}
