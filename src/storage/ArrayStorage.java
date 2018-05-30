package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    public void delete(String uuid) {
        int storageIndex = getIndex(uuid);

        if (storageIndex >= 0) {
            size--;
            storage[storageIndex] = storage[size];
            storage[size] = null;
        } else {
            System.out.println("Resume doesn't exists in storage");
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

    @Override
    protected void insert(Resume resume) {
        storage[size] = resume;
    }

}
