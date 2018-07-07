package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getKey(String uuid);

    protected abstract void doUpdate(Object key, Resume resume);

    protected abstract boolean isExists(Object key);

    protected abstract void doSave(Object key, Resume resume);

    protected abstract Resume doGet(Object key);

    protected abstract void doDelete(Object key);

    abstract Stream<Resume> getStreamForSort();

    @Override
    public final void update(Resume resume) {
        Object key = getIfExist(resume.getUuid());
        doUpdate(key, resume);
    }

    @Override
    public final void save(Resume resume) {
        Object key = getIfNotExist(resume.getUuid());
        doSave(key, resume);
    }

    @Override
    public final Resume get(String uuid) {
        Object key = getIfExist(uuid);
        return doGet(key);
    }

    @Override
    public final void delete(String uuid) {
        Object key = getIfExist(uuid);
        doDelete(key);
    }

    private Object getIfExist(String uuid) {
        if (!isExists(getKey(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    private Object getIfNotExist(String uuid) {
        if (isExists(getKey(uuid))) {
            throw new ExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        return getStreamForSort()
                .sorted(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid))
                .collect(Collectors.toList());
    }
}
