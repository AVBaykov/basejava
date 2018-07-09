package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractStorage<SK> implements Storage {

    protected abstract SK getKey(String uuid);

    protected abstract void doUpdate(SK key, Resume resume);

    protected abstract boolean isExists(SK key);

    protected abstract void doSave(SK key, Resume resume);

    protected abstract Resume doGet(SK key);

    protected abstract void doDelete(SK key);

    abstract Stream<Resume> getStreamForSort();

    @Override
    public final void update(Resume resume) {
        SK key = getIfExist(resume.getUuid());
        doUpdate(key, resume);
    }

    @Override
    public final void save(Resume resume) {
        SK key = getIfNotExist(resume.getUuid());
        doSave(key, resume);
    }

    @Override
    public final Resume get(String uuid) {
        SK key = getIfExist(uuid);
        return doGet(key);
    }

    @Override
    public final void delete(String uuid) {
        SK key = getIfExist(uuid);
        doDelete(key);
    }

    private SK getIfExist(String uuid) {
        if (!isExists(getKey(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    private SK getIfNotExist(String uuid) {
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
