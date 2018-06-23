package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {


    @Override
    public void update(Resume resume) {
        Object key = checkForNotExist(resume.getUuid());
        rewrite(key, resume);
    }

    @Override
    public void save(Resume resume) {
        Object key = checkForExist(resume.getUuid());
        saveResume(key, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object key = checkForNotExist(uuid);
        return getResume(key);
    }

    @Override
    public void delete(String uuid) {
        Object key = checkForNotExist(uuid);
        deleteResume(key);
    }

    private Object checkForNotExist(String uuid) {
        if (!isResumePresent(getKey(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    protected Object checkForExist(String uuid) {
        if (isResumePresent(getKey(uuid))) {
            throw new ExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    protected abstract boolean isResumePresent(Object key);

    protected abstract Resume getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract void saveResume(Object key, Resume resume);

    protected abstract Object getKey(String uuid);

    protected abstract void rewrite(Object key, Resume resume);
}
