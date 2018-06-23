package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {


    @Override
    public void update(Resume resume) {
        Object key = getKeyOrNotExistException(resume.getUuid());
        rewrite(key, resume);
    }

    @Override
    public void save(Resume resume) {
        Object key = getKeyOrExistException(resume.getUuid());
        saveResume(key, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getKeyOrNotExistException(uuid);
        return getResume(key);
    }

    @Override
    public void delete(String uuid) {
        Object key = getKeyOrNotExistException(uuid);
        deleteResume(key);
    }

    private Object getKeyOrNotExistException(String uuid) {
        if (!isResumePresent(getKey(uuid))) throw new NotExistStorageException(uuid);
        return getKey(uuid);
    }

    protected Object getKeyOrExistException(String uuid) {
        if (isResumePresent(getKey(uuid))) throw new ExistStorageException(uuid);
        return getKey(uuid);
    }

    protected abstract boolean isResumePresent(Object key);

    protected abstract Resume getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract void saveResume(Object key, Resume resume);

    protected abstract Object getKey(String uuid);

    protected abstract void rewrite(Object key, Resume resume);
}
