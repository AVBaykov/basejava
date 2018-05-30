package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;


    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int storageIndex = getIndex(resume.getUuid());

        if (storageIndex < 0) {
            System.out.println("Resume doesn't exists in storage");
        } else {
            storage[storageIndex] = resume;
        }
    }

    public void save(Resume resume) {
        if (storage.length == size) {
            System.out.println("Exceeded storage capacity. You must delete at least one resume");
            return;
        }
        if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume already exists in base");
            return;
        }
        insert(resume);
        size++;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume doesn't exists in storage");
            return null;
        }
        return storage[index];
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            System.out.println("Resume doesn't exists in storage");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size -1 -index );
            size--;
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insert(Resume resume);
}
