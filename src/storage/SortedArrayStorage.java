package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void delete(String uuid) {

    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insert(Resume resume) {
        int insertIndex = - (Arrays.binarySearch(storage, 0,size,resume)) - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex+1,size-insertIndex);
        storage[insertIndex] = resume;
    }
}
