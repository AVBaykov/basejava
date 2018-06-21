package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {


    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object key = getKey(uuid);
        notExistStorageExceptionThrower(key, uuid);
        rewrite(key, resume);
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object key = getKey(uuid);
        existStorageExceptionThrower(key, uuid);
        saveResume(key, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getKey(uuid);
        notExistStorageExceptionThrower(key, uuid);
        return getResume(key);
    }

    @Override
    public void delete(String uuid) {
        Object key = getKey(uuid);
        notExistStorageExceptionThrower(key, uuid);
        deleteResume(key);
    }

    private void notExistStorageExceptionThrower(Object key, String uuid) {
        if (!isResumePresent(key)) throw new NotExistStorageException(uuid);
    }

    protected void existStorageExceptionThrower(Object key, String uuid) {
        if (isResumePresent(key)) throw new ExistStorageException(uuid);
    }

    protected abstract boolean isResumePresent(Object key);

    protected abstract Resume getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract void saveResume(Object key, Resume resume);

    protected abstract Object getKey(String uuid);

    protected abstract void rewrite(Object key, Resume resume);
}
