import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    private int size;

    void clear() {
        Arrays.fill(storage, 0,size, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {

            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                size--;
                break;
            }
        }
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
