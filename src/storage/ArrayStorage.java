package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    public void save(Resume resume) {
        if (storage.length == size) {
            System.out.println("Exceeded storage capacity. You must delete at least one resume");
            return;
        }
        if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("model.Resume already exists in base");
            return;
        }
        storage[size] = resume;
        size++;
    }

    public void update(Resume resume) {
        int storageIndex = getIndex(resume.getUuid());

        if (storageIndex < 0) {
            System.out.println("model.Resume doesn't exists in storage");
        } else {
            storage[storageIndex] = resume;

        }
    }

    public Resume get(String uuid) {
        int storageIndex = getIndex(uuid);

        if (storageIndex < 0) {
            System.out.println("model.Resume doesn't exists in storage");
            return null;
        }
        return storage[storageIndex];
    }

    public void delete(String uuid) {
        int storageIndex = getIndex(uuid);

        if (storageIndex >= 0) {
            size--;
            storage[storageIndex] = storage[size];
            storage[size] = null;
        } else {
            System.out.println("model.Resume doesn't exists in storage");
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
