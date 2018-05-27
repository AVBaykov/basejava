import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (storage.length == size) {
            System.out.println("Exceeded storage capacity. You must delete at least one resume");
            return;
        }
        if (getStorageIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume already exists in base");
            return;
        }
        storage[size] = resume;
        size++;
    }

    public void update(Resume resume) {
        int storageIndex = getStorageIndex(resume.getUuid());

        if (storageIndex < 0) {
            System.out.println("Resume doesn't exists in storage");
        } else {
            storage[storageIndex] = resume;
        }
    }

    public Resume get(String uuid) {
        int storageIndex = getStorageIndex(uuid);

        if (storageIndex < 0) {
            System.out.println("Resume doesn't exists in storage");
            return null;
        }
        return storage[storageIndex];
    }

    public void delete(String uuid) {
        int storageIndex = getStorageIndex(uuid);

        if (storageIndex >= 0) {
            size--;
            storage[storageIndex] = storage[size];
            storage[size] = null;
        } else {
            System.out.println("Resume doesn't exists in storage");
        }
    }

    private int getStorageIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
