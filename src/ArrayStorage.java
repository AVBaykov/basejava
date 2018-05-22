import java.util.ArrayList;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

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
                storage[i] = storage[i+1];

                for (int j = i+1; j < size; j++) {
                    storage[j] = storage[j+1];
                }
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
