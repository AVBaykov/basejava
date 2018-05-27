import java.sql.SQLOutput;
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

        if (storage.length == size) {
            System.out.println("Exceeded storage capacity. You must delete at least one resume");
            return;
        }

        if (isResumePresent(r.uuid) >= 0) {
            System.out.println("Resume already exists in base");
            return;
        }

        storage[size] = r;
        size++;
    }

    void update(Resume r) {
        int i = isResumePresent(r.uuid);

        if (i < 0) {
            System.out.println("Resume doesn't exists in storage");
        } else {
            storage[i] = r;
        }
    }

    Resume get(String uuid) {
        int i = isResumePresent(uuid);
        if (i < 0) {
            System.out.println("Resume doesn't exists in storage");
            return null;
        }
        else return storage[i];
    }

    void delete(String uuid) {
        int i = isResumePresent(uuid);

        if (i >= 0 ) {
            System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume doesn't exists in storage");
        }
    }

    private int isResumePresent(String uuid) {

        for (int i = 0; i < size ; i++) {
            if (storage[i].uuid.equals(uuid)) {
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
