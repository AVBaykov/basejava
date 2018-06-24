package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {


    @Override
    public final void update(Resume resume) {
        Object key = getIfExist(resume.getUuid());
        rewrite(key, resume);
    }

    @Override
    public final void save(Resume resume) {
        Object key = getIfNotExist(resume.getUuid());
        saveResume(key, resume);
    }

    @Override
    public final Resume get(String uuid) {
        Object key = getIfExist(uuid);
        return getResume(key);
    }

    @Override
    public final void delete(String uuid) {
        Object key = getIfExist(uuid);
        deleteResume(key);
    }

    private Object getIfExist(String uuid) {
        if (!isPresent(getKey(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    private Object getIfNotExist(String uuid) {
        if (isPresent(getKey(uuid))) {
            throw new ExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    protected abstract boolean isPresent(Object key);

    protected abstract Resume getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract void saveResume(Object key, Resume resume);

    protected abstract Object getKey(String uuid);

    protected abstract void rewrite(Object key, Resume resume);
}
