import java.util.ArrayList;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        storage = new Resume[10000];
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length ; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        Resume absence = new Resume();
        absence.uuid = "Нет резюме с таким id";
        return absence;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) break;

            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[i+1];

                for (int j = i+1; j < size(); j++) {
//                    storage[j] = storage[j+1];
                }

                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (size() > 0) {
            return Arrays.copyOf(storage, size());
        }
        return new Resume[0];
    }

    int size() {
        int count = 0;

        for (Resume res : storage) {
            if (res != null) count++;
            else break;
        }

        return count;
    }
}
